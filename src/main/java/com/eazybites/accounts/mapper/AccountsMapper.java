package com.eazybites.accounts.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.eazybites.accounts.model.dto.response.AccountResponseDto;
import com.eazybites.accounts.model.entity.Accounts;

@Mapper(componentModel = "spring")
public interface AccountsMapper extends AuditableMappingConfig<AccountResponseDto, Accounts> {
    @InheritConfiguration(name = "mapAudit")
    @Mapping(target = "customerId", ignore = true)
    Accounts mapToAccount(AccountResponseDto dto);

    AccountResponseDto mapToAccountDto(Accounts account);

}
