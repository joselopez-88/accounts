package com.eazybites.accounts.service.impl;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.eazybites.accounts.constants.AccountsConstants;
import com.eazybites.accounts.exception.error.CustomerAlreadyExistsException;
import com.eazybites.accounts.exception.error.ResourceNotFoundException;
import com.eazybites.accounts.mapper.AccountsMapper;
import com.eazybites.accounts.mapper.CustomerMapper;
import com.eazybites.accounts.model.dto.message.AccountsMsgDto;
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

    private static final Logger logger = LoggerFactory.getLogger(AccountsServiceImpl.class);
    
    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AccountsMapper accountsMapper;

    private final StreamBridge streamBridge;

    
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
            throw new CustomerAlreadyExistsException(customerDto.getMobileNumber());
        }

        Customer savedCustomer = customerRepository.save(customer);
        Accounts savedAccount = accountsRepository.save(createNewAccount(savedCustomer));
        sendCommunication(savedAccount, savedCustomer);
    }

       /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        Accounts account = new Accounts();
        account.setCustomerId(customer.getCustomerId());
        account.setAccountNumber(randomAccNumber);
        account.setAccountType(AccountsConstants.SAVINGS);
        account.setBranchAddress(AccountsConstants.ADDRESS);

        return account;        
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

        @Override
        public void deleteAccount(String mobileNumber) {
            Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                    .orElseThrow(()-> new ResourceNotFoundException("Customer","mobileNumber ", mobileNumber));
            accountsRepository.deleteByCustomerId(customer.getCustomerId());
            customerRepository.deleteById(customer.getCustomerId());
        }

        private void sendCommunication(Accounts account, Customer customer){
            var accountsMsgDto = new AccountsMsgDto(account.getAccountNumber(), customer.getName(), customer.getEmail(), customer.getMobileNumber());
            logger.info("Sending communication request for the details: {}", accountsMsgDto);
            var result = streamBridge.send("sendCommunication-out-0", accountsMsgDto);
            logger.info("Is the communication request successfuy processed: {}", result);

        }

        @Override
        public boolean updateCommunicationStatus(Long accountNumber) {
            boolean isUpdated = false;
            if(accountNumber != null){
                Accounts account = accountsRepository.findById(accountNumber)
                    .orElseThrow(()-> new ResourceNotFoundException("Account","accountNumber ", String.valueOf(accountNumber)));
                account.setCommunicationSw(true);
                accountsRepository.save(account);
                isUpdated = true;
            }
            return isUpdated;
        }
}
