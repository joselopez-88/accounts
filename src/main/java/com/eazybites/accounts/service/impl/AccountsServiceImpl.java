package com.eazybites.accounts.service.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.eazybites.accounts.constants.AccountsConstants;
import com.eazybites.accounts.exception.error.CustomerAlreadyExistsException;
import com.eazybites.accounts.mapper.CustomerMapper;
import com.eazybites.accounts.model.dto.request.CustomerDto;
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
    @Override
    public void createAccount(CustomerDto customerDto) {
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

}
