package com.eazybites.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eazybites.accounts.constants.AccountsConstants;
import com.eazybites.accounts.dto.request.AccountsDto;
import com.eazybites.accounts.dto.response.ResponseDto;

@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountsController {
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody AccountsDto accountsDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(String.valueOf(HttpStatus.CREATED.value()), AccountsConstants.MESSAGE_201));
    }
}
