package com.eazybites.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazybites.accounts.constants.AccountsConstants;
import com.eazybites.accounts.model.dto.request.CustomerRequestDto;
import com.eazybites.accounts.model.dto.response.CustomerResponseDto;
import com.eazybites.accounts.model.dto.response.ResponseDto;
import com.eazybites.accounts.service.IAccountsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class AccountsController {

    private final IAccountsService accountsService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerRequestDto customerDto) {
        accountsService.createAccount(customerDto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                new ResponseDto(HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED.value(), AccountsConstants.MESSAGE_201)
            );
    }
    @GetMapping("/fetch")
    public ResponseEntity<CustomerResponseDto> fetchAccountDetails(@RequestParam String mobileNumber) {
        return ResponseEntity.ok().body(accountsService.fetchAccount(mobileNumber));
    }
}
