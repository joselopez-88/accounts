package com.eazybites.accounts.service;

import com.eazybites.accounts.model.dto.response.CustomerDetailsDto;

public interface ICustomerService {

    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
