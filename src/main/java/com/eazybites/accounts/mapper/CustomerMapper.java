package com.eazybites.accounts.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.eazybites.accounts.model.dto.request.CustomerAccountUpdateRequestDto;
import com.eazybites.accounts.model.dto.request.CustomerCreateRequestDto;
import com.eazybites.accounts.model.dto.response.CustomerResponseDto;
import com.eazybites.accounts.model.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper    {
    @Mapping(target = "account", ignore = true)
    CustomerResponseDto mapToCustomerDto(Customer customer);
    
    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    Customer mapToCustomer(CustomerResponseDto customerDto);

    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true) 
    Customer mapToCustomer(CustomerCreateRequestDto customerDto);

    @Mapping(target = "customerId", ignore = true) // Ignorar ID
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true) 
    void updateCustomerFromDto(CustomerAccountUpdateRequestDto dto, @MappingTarget Customer customer);
}
