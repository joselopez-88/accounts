package com.eazybites.accounts.service;

import com.eazybites.accounts.model.dto.request.CustomerRequestDto;
import com.eazybites.accounts.model.dto.response.CustomerResponseDto;

public interface IAccountsService {
 /**
  * 
  * @param customerDto - CustomerDto Object
  */
 void createAccount(CustomerRequestDto customerDto);

 /**
  * 
  * @param mobileNumber - Input mobileNumber
  * @return Accounts Details for the given mobileNumber
  */

 CustomerResponseDto fetchAccount(String mobileNumber);
}
