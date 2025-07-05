package com.eazybites.accounts.controller;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazybites.accounts.constants.AccountsConstants;
import com.eazybites.accounts.model.dto.AccountsContactInfoDto;
import com.eazybites.accounts.model.dto.request.CustomerAccountUpdateRequestDto;
import com.eazybites.accounts.model.dto.request.CustomerCreateRequestDto;
import com.eazybites.accounts.model.dto.response.CustomerResponseDto;
import com.eazybites.accounts.model.dto.response.ResponseDto;
import com.eazybites.accounts.service.IAccountsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
@Tag(
    name = "Accounts",
    description = "CRUD Rest API for Accounts in EazyBank to Create, Fetch, Update and Delete account details"
)
@RestController
@RequestMapping(path="/api/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated // Active the validation in all method parameters in the controller
public class AccountsController {

    private final IAccountsService accountsService;

    @Value("${build.version}")
    private String buildVersion;

    private final Environment environment;

    private final AccountsContactInfoDto contactInfo;

    @Operation(
        summary = "Create Account Rest Api",
        description = "Create a new customer and account in EazyBank"
    )
    @ApiResponse(responseCode = "201", description = "Created")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerCreateRequestDto customerDto) {
        accountsService.createAccount(customerDto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                new ResponseDto(HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED.value(), AccountsConstants.MESSAGE_201)
            );
    }
    @Operation(
        summary = "Fetch Account Rest Api",
        description = "Fetch Customer and Account details in EazyBank"
    )
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/fetch")
    public ResponseEntity<CustomerResponseDto> fetchAccountDetails(@RequestParam @Pattern(regexp = "(^[0-9]{10}$)", message = " The mobile number must be 10 digits.") String mobileNumber) {
        return ResponseEntity.ok().body(accountsService.fetchAccount(mobileNumber));
    }

    @Operation(
        summary = "Update Account Rest Api",
        description = "Update Customer and Account details in EazyBank"
    )
    // @ApiResponses(
    //     value = {
    //         @ApiResponse(responseCode = "200", description = "OK"),
    //         @ApiResponse(responseCode = "400", description = "Bad Request"),
    //         @ApiResponse(responseCode = "404", description = "Not Found"),
    //         @ApiResponse(responseCode = "500", description = "Internal Server Error")
    //     }
    // )
     @PatchMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerAccountUpdateRequestDto customerDetails) {
        accountsService.updateAccount(customerDetails);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                new ResponseDto(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), AccountsConstants.MESSAGE_200)
            );
    }
    @Operation(
        summary = "Delete Account Rest Api",
        description = "Delete Customer and Account details in EazyBank"
    )
    // @ApiResponses(
    //     value = {
    //         @ApiResponse(responseCode = "200", description = "OK"),
    //         @ApiResponse(responseCode = "400", description = "Bad Request"),
    //         @ApiResponse(responseCode = "404", description = "Not Found"),
    //         @ApiResponse(responseCode = "500", description = "Internal Server Error")
    //     }
    // )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam @Pattern(regexp = "(^[0-9]{10}$)", message = " The mobile number must be 10 digits.") String mobileNumber){
        accountsService.deleteAccount(mobileNumber);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                new ResponseDto(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), AccountsConstants.MESSAGE_200)
            );
    }

    @Operation(
        summary = "Get Build Version Rest Api",
        description = "Get Build Version details in EazyBank"
    )
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildVersion(){
        return ResponseEntity.ok().body(buildVersion);
    }


    @Operation(
      summary = "Get Java Home Rest Api",
      description = "Get Java Home details in EazyBank"
    )
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/java-home")
    public ResponseEntity<String> getJavaHome(){
        return ResponseEntity.ok().body(environment.getProperty("JAVA_HOME"));
    }


    @Operation(
        summary = "Get Contact Info Rest Api",
        description = "Get Contact Info Details that can be reached out in case of any issue"
    )
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
        return ResponseEntity.ok().body(contactInfo);
    }
}
