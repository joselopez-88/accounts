package com.eazybites.accounts.service.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.eazybites.accounts.constants.AccountsConstants;
import com.eazybites.accounts.exception.error.CustomerAlreadyExistsException;
import com.eazybites.accounts.exception.error.ResourceNotFoundException;
import com.eazybites.accounts.mapper.AccountsMapper;
import com.eazybites.accounts.mapper.CustomerMapper;
import com.eazybites.accounts.model.dto.request.CustomerAccountUpdateRequestDto;
import com.eazybites.accounts.model.dto.request.CustomerCreateRequestDto;
import com.eazybites.accounts.model.dto.response.AccountResponseDto;
import com.eazybites.accounts.model.dto.response.CustomerResponseDto;
import com.eazybites.accounts.model.entity.Accounts;
import com.eazybites.accounts.model.entity.Customer;
import com.eazybites.accounts.repository.AccountsRepository;
import com.eazybites.accounts.repository.CustomerRepository;
import com.eazybites.accounts.service.IAccountsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AccountsMapper accountsMapper;

    
    /**
     * Create a new customer account.
     * 
     * @param customerDto Customer details such as name, email and mobileNumber
     * 
     * @throws CustomerAlreadyExistsException if the customer is already registered
     *                                         with the given mobileNumber
     */
    @Override
    public void createAccount(CustomerCreateRequestDto customerDto) {
        Customer customer = customerMapper.mapToCustomer(customerDto);
        boolean existsMobile = customerRepository.existsByMobileNumber(customer.getMobileNumber());

        if(existsMobile){
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
                    +customerDto.getMobileNumber());
        }

        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

       /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        return Accounts.builder()
        .customerId(customer.getCustomerId())
        .accountNumber(randomAccNumber)
        .accountType(AccountsConstants.SAVINGS)
        .branchAddress(AccountsConstants.ADDRESS)
        .build();
        
    }

        /**
         * Fetch customer account details.
         * 
         * @param mobileNumber Customer mobileNumber
         * 
         * @return Customer account details
         * 
         * @throws ResourceNotFoundException if the customer or account is not found
         *                                     for the given mobileNumber
         */
       @Override
       public CustomerResponseDto fetchAccount(String mobileNumber) {
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
        return customerResponseDto;
       }

        @Override
        public void updateAccount(CustomerAccountUpdateRequestDto customerDetails) {
            Customer customer = customerRepository.findById(customerDetails.getCustomerId())
                    .orElseThrow(()-> new ResourceNotFoundException("Customer","id ", String.valueOf(customerDetails.getCustomerId())));
            Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                    .orElseThrow(()-> new ResourceNotFoundException("Account","customer id ", String.valueOf(customer.getCustomerId())));
            
           accountsMapper.updateAccountFromDto(customerDetails.getAccount(), accounts);
           customerMapper.updateCustomerFromDto(customerDetails, customer);
            
            customerRepository.save(customer);
            accountsRepository.save(accounts);
        }

}
