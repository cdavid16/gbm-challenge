package com.gbm.challenge.gbmchallenge.controller;

import com.gbm.challenge.gbmchallenge.factory.MapperResponseFactory;
import com.gbm.challenge.gbmchallenge.model.entities.TransactionEntity;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import com.gbm.challenge.gbmchallenge.model.response.SendOrderResponse;
import com.gbm.challenge.gbmchallenge.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/accounts/{id}/orders")
    public HttpEntity<SendOrderResponse> sendOrder(@PathVariable @Validated final String accountId,
                                                   @RequestBody @Validated final SendOrderDto request){
        TransactionEntity transactionEntity = transactionService.processTransaction(request, accountId);
        SendOrderResponse responseEntity = MapperResponseFactory.createPositiveResponse(transactionEntity, SendOrderResponse.class);
        return new HttpEntity<>(responseEntity);
    }

}
