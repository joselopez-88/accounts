package com.eazybites.accounts.mapper;

import org.mapstruct.Mapper;

import com.eazybites.accounts.model.dto.request.CustomerDto;
import com.eazybites.accounts.model.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto mapToCustomerDto(Customer customer);
    Customer mapToCustomer(CustomerDto customerDto);
}
