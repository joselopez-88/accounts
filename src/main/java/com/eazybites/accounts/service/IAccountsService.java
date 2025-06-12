package com.eazybites.accounts.service;

import com.eazybites.accounts.dto.request.CustomerDto;

public interface IAccountsService {
 /**
  * 
  * @param customerDto - CustomerDto Object
  */
 void createAccount(CustomerDto customerDto);
}
