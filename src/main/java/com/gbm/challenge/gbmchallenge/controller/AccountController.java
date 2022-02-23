package com.gbm.challenge.gbmchallenge.controller;

import com.gbm.challenge.gbmchallenge.factory.CreateAccountResponseFactory;
import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.model.request.CreateAccountDto;
import com.gbm.challenge.gbmchallenge.model.response.CreateAccountResponse;
import com.gbm.challenge.gbmchallenge.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController("/accounts")
public class AccountController {

    private IAccountService accountService;

    @Autowired
    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.OK)
    public HttpEntity<CreateAccountResponse> createAccount(@RequestBody @Validated final CreateAccountDto request){
        AccountEntity accountEntity = accountService.createAccount(request.getCash());
        CreateAccountResponse responseEntity = CreateAccountResponseFactory.CreateCreateAccountResponse(accountEntity);
        return new HttpEntity<>(responseEntity);
    }

}
