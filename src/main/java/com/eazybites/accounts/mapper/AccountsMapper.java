package com.eazybites.accounts.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.eazybites.accounts.model.dto.request.AccountsDto;
import com.eazybites.accounts.model.entity.Accounts;

@Mapper(componentModel = "spring")
public interface AccountsMapper {
    AccountsDto mapToAccountsDto(Accounts accounts);
    @Mapping(target = "accountNumber", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    Accounts mapToAccounts(AccountsDto accountsDto);

}
