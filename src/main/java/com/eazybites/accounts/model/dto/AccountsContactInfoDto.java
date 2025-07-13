package com.eazybites.accounts.model.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;
@ConfigurationProperties(prefix = "accounts")// Esto sirve para tomar los datos del application.properties o yml con el prefijo accounts con los nombres de las propiedades
@Getter
@Setter
public class AccountsContactInfoDto {

    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;

}
