package com.gbm.challenge.gbmchallenge.service;

import com.gbm.challenge.gbmchallenge.enums.OperationEnum;
import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.model.entities.TransactionDetailEntity;
import com.gbm.challenge.gbmchallenge.model.entities.TransactionEntity;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import org.springframework.web.context.request.WebRequest;

import java.util.Set;

public interface TransactionService {

    TransactionEntity processTransaction(SendOrderDto orderRequest, String accountId, WebRequest webRequest);

    TransactionEntity createTransaction(AccountEntity account, Long timestamp);

    TransactionDetailEntity createTransactionDetails(TransactionEntity transaction, String issuerName,
                                                     OperationEnum operation, Long quantity, Double price);

}
