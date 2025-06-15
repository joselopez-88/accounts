package com.eazybites.accounts.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.eazybites.accounts.model.dto.request.AccountsRequestDto;
import com.eazybites.accounts.model.dto.response.AccountResponseDto;
import com.eazybites.accounts.model.entity.Accounts;

@Mapper(componentModel = "spring")
public interface AccountsMapper {
    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    Accounts mapToAccount(AccountResponseDto dto);

    AccountResponseDto mapToAccountDto(Accounts account);

    @Mapping(target = "accountNumber", ignore = true)
    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    void updateAccountFromDto(AccountsRequestDto dto, @MappingTarget Accounts entity);

}
