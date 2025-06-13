package com.eazybites.accounts.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@MapperConfig(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuditIgnoringConfig {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    void ignoreAuditFields(Object target);
}