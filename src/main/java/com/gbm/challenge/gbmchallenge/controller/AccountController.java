package com.gbm.challenge.gbmchallenge.controller;

import com.gbm.challenge.gbmchallenge.model.request.CreateAccountDto;
import com.gbm.challenge.gbmchallenge.model.response.CreateAccountResponse;
import org.springframework.http.HttpEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/accounts")
public class AccountController {

    @PostMapping
    public HttpEntity<CreateAccountResponse> createAccount(@RequestBody @Validated final CreateAccountDto request){
        return null;
    }

}
