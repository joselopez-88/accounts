package com.eazybites.accounts.model.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
@ConfigurationProperties(prefix = "accounts")// Esto sirve para tomar los datos del application.properties o yml con el prefijo accounts con los nombres de las propiedades
public record AccountsContactInfoDto(String message, Map<String, String> contactDetails, List<String> onCallSupport) {


}
