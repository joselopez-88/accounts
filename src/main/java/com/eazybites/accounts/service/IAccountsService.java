package com.eazybites.accounts.service;

import com.eazybites.accounts.model.dto.request.CustomerAccountUpdateRequestDto;
import com.eazybites.accounts.model.dto.request.CustomerCreateRequestDto;
import com.eazybites.accounts.model.dto.response.CustomerResponseDto;

public interface IAccountsService {
 /**
  * 
  * @param customerDto - CustomerDto Object
  */
 void createAccount(CustomerCreateRequestDto customerDto);

 /**
  * 
  * @param mobileNumber - Input mobileNumber
  * @return Accounts Details for the given mobileNumber
  */

 CustomerResponseDto fetchAccount(String mobileNumber);

 /**
  * 
  * @param customerDetails - CustomerAccountUpdateRequestDto Object
  * Updates the customer and account details
  */
 void updateAccount(CustomerAccountUpdateRequestDto customerDetails);

 /**
  * 
  * @param mobileNumber - Input mobileNumber
  * Deletes the account for the given mobileNumber
  */
 void deleteAccount(String mobileNumber);

}
