package com.gbm.challenge.gbmchallenge.controller;

import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import org.springframework.http.HttpEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @PostMapping("/accounts/{id}/orders")
    public HttpEntity<SendOrderDto> sendOrder(@PathVariable @Validated final Long accountId,
                                              @RequestBody @Validated final SendOrderDto request){
        return null;
    }

}
