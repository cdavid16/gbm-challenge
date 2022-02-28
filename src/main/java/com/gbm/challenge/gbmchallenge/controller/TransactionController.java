package com.gbm.challenge.gbmchallenge.controller;

import com.gbm.challenge.gbmchallenge.factory.MapperResponseFactory;
import com.gbm.challenge.gbmchallenge.model.entities.TransactionEntity;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import com.gbm.challenge.gbmchallenge.model.response.SendOrderResponse;
import com.gbm.challenge.gbmchallenge.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(final TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/accounts/{accountId}/orders")
    public HttpEntity<SendOrderResponse> sendOrder(@PathVariable @Validated final String accountId,
                                                   @RequestBody @Validated final SendOrderDto request,
                                                   final WebRequest webRequest){
        log.info("Request: {}, accountId: {}", request, accountId);
        TransactionEntity transactionEntity = transactionService.processTransaction(request, accountId, webRequest);
        SendOrderResponse responseEntity = MapperResponseFactory.createPositiveResponse(transactionEntity, SendOrderResponse.class);
        log.info("Response: {}", responseEntity);
        return new HttpEntity<>(responseEntity);
    }

}
