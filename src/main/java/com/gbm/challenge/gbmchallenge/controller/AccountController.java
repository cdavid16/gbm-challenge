package com.gbm.challenge.gbmchallenge.controller;

import com.gbm.challenge.gbmchallenge.factory.MapperResponseFactory;
import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.model.request.CreateAccountDto;
import com.gbm.challenge.gbmchallenge.model.response.CreateAccountResponse;
import com.gbm.challenge.gbmchallenge.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(code = HttpStatus.OK)
    public HttpEntity<CreateAccountResponse> createAccount(@RequestBody @Validated final CreateAccountDto request){
        log.info("Request: {}", request);
        AccountEntity accountEntity = accountService.createAccount(request.getCash());
        CreateAccountResponse responseEntity = MapperResponseFactory.createPositiveResponse(accountEntity, CreateAccountResponse.class);
        log.info("Response: {}", responseEntity);
        return new HttpEntity<>(responseEntity);
    }

}
