package com.eazybites.accounts.mapper;

import org.mapstruct.Mapper;

import com.eazybites.accounts.model.dto.request.AccountsDto;
import com.eazybites.accounts.model.entity.Accounts;

@Mapper(componentModel = "spring")
public interface AccountsMapper {
    AccountsDto mapToAccountsDto(Accounts accounts);
    Accounts mapToAccounts(AccountsDto accountsDto);

}
