package com.eazybites.accounts.service.impl;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eazybites.accounts.exception.error.ResourceNotFoundException;
import com.eazybites.accounts.mapper.AccountsMapper;
import com.eazybites.accounts.mapper.CustomerMapper;
import com.eazybites.accounts.model.dto.client.CardsResponseDto;
import com.eazybites.accounts.model.dto.client.LoansResponseDto;
import com.eazybites.accounts.model.dto.response.AccountResponseDto;
import com.eazybites.accounts.model.dto.response.CustomerDetailsDto;
import com.eazybites.accounts.model.dto.response.CustomerResponseDto;
import com.eazybites.accounts.model.entity.Accounts;
import com.eazybites.accounts.model.entity.Customer;
import com.eazybites.accounts.repository.AccountsRepository;
import com.eazybites.accounts.repository.CustomerRepository;
import com.eazybites.accounts.service.ICustomerService;
import com.eazybites.accounts.service.client.CardsFeignClient;
import com.eazybites.accounts.service.client.LoansFeignClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final AccountsRepository accountsRepository;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;

    private final CustomerMapper customerMapper;
    private final AccountsMapper accountsMapper;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository
            .findByMobileNumber(mobileNumber)
            .orElseThrow( ()-> new ResourceNotFoundException("Customer","mobileNumber ", mobileNumber));   
        Accounts account = accountsRepository.findByCustomerId(
                    customer.getCustomerId())
                    .orElseThrow(
                        ()-> new ResourceNotFoundException("Account","id ", String.valueOf(customer.getCustomerId())));
        
                        CustomerResponseDto customerResponseDto = customerMapper.mapToCustomerDto(customer);
        AccountResponseDto accountResponseDto = accountsMapper.mapToAccountDto(account);
        customerResponseDto.setAccount(accountResponseDto);       
        
        Optional<ResponseEntity<CardsResponseDto>> optCardsResponseDto = Optional.ofNullable(cardsFeignClient.fetchCardDetails(correlationId,mobileNumber));
        Optional<ResponseEntity<LoansResponseDto>> optLoansResponseDto = Optional.ofNullable(loansFeignClient.fetchLoan(correlationId, mobileNumber));

        CardsResponseDto cardsResponseDto = optCardsResponseDto.map(ResponseEntity::getBody).orElse(null);
        LoansResponseDto loansResponseDto = optLoansResponseDto.map(ResponseEntity::getBody).orElse(null);

        CustomerDetailsDto customerDetailsDto = new CustomerDetailsDto();
        customerDetailsDto.setCustomer(customerResponseDto);
        customerDetailsDto.setCards(cardsResponseDto);
        customerDetailsDto.setLoans(loansResponseDto);
        
        return customerDetailsDto;
    }
}
