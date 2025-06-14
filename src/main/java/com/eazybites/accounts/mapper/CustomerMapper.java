package com.eazybites.accounts.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.eazybites.accounts.model.dto.request.CustomerRequestDto;
import com.eazybites.accounts.model.dto.response.CustomerResponseDto;
import com.eazybites.accounts.model.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends AuditableMappingConfig<CustomerResponseDto, Customer> {
    @Mapping(target = "account", ignore = true)
    CustomerResponseDto mapToCustomerDto(Customer customer);
    
    @InheritConfiguration(name = "mapAudit")
    Customer mapToCustomer(CustomerResponseDto customerDto);

     @InheritConfiguration(name = "mapAudit")
    Customer mapToCustomer(CustomerRequestDto customerDto);
}
