package com.eazybites.accounts.functions;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.eazybites.accounts.service.IAccountsService;

@Configuration
public class AccountFunctions {
    private static final Logger logger = LoggerFactory.getLogger(AccountFunctions.class);

    @Bean
    public Consumer<Long> updateCommunication(IAccountsService accountsService){
        return accountNumber ->{
            logger.info("Updating communication status with the account number: {}", accountNumber);
            accountsService.updateCommunicationStatus(accountNumber);};
    }
}
