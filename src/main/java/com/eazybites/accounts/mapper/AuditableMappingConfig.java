package com.eazybites.accounts.mapper;

import org.mapstruct.Mapping;

public interface AuditableMappingConfig<S, T> {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    T mapAudit(S source);
}