package com.gbm.challenge.gbmchallenge.service;

import com.gbm.challenge.gbmchallenge.enums.OperationEnum;
import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.model.entities.TransactionEntity;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import org.springframework.web.context.request.WebRequest;

public interface TransactionService {

    TransactionEntity processTransaction(SendOrderDto orderRequest, String accountId, WebRequest webRequest);

    TransactionEntity createTransaction(AccountEntity account, Long timestamp);

    void createTransactionDetails(TransactionEntity transaction, String issuerName,
                                                     OperationEnum operation, Long quantity, Double price);

}
