package com.gbm.challenge.gbmchallenge.controller;

import com.gbm.challenge.gbmchallenge.factory.CreateAccountResponseFactory;
import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.model.request.CreateAccountDto;
import com.gbm.challenge.gbmchallenge.model.response.CreateAccountResponse;
import com.gbm.challenge.gbmchallenge.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        AccountEntity accountEntity = accountService.createAccount(request.getCash());
        CreateAccountResponse responseEntity = CreateAccountResponseFactory.CreateCreateAccountResponse(accountEntity);
        return new HttpEntity<>(responseEntity);
    }

}
