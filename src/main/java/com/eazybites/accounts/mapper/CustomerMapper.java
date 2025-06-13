package com.eazybites.accounts.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.eazybites.accounts.model.dto.request.CustomerDto;
import com.eazybites.accounts.model.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper  {
    CustomerDto mapToCustomerDto(Customer customer);
    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    Customer mapToCustomer(CustomerDto customerDto);
}
