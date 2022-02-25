package com.gbm.challenge.gbmchallenge.service;

import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.model.entities.TransactionDetailEntity;
import com.gbm.challenge.gbmchallenge.model.entities.TransactionEntity;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;

public interface TransactionService {

    TransactionEntity processTransaction(SendOrderDto orderRequest, String accountId);

    TransactionEntity createTransaction(AccountEntity account, Long timestamp);

    TransactionDetailEntity createTransactionDetails(TransactionEntity transaction, String issuerName,
                                                     String operation, Long quantity, Double price);

}
