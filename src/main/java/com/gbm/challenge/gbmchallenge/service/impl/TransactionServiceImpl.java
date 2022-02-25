package com.gbm.challenge.gbmchallenge.service.impl;

import com.gbm.challenge.gbmchallenge.enums.TransactionEnum;
import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.model.entities.IssuerEntity;
import com.gbm.challenge.gbmchallenge.model.entities.TransactionDetailEntity;
import com.gbm.challenge.gbmchallenge.model.entities.TransactionEntity;
import com.gbm.challenge.gbmchallenge.model.request.OperationDto;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import com.gbm.challenge.gbmchallenge.repository.AccountRepository;
import com.gbm.challenge.gbmchallenge.repository.IssuerRepository;
import com.gbm.challenge.gbmchallenge.repository.TransactionRepository;
import com.gbm.challenge.gbmchallenge.service.TransactionService;
import com.gbm.challenge.gbmchallenge.utils.TransactionHelper;
import com.gbm.challenge.gbmchallenge.validator.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final IssuerRepository issuerRepository;

    @Autowired
    public TransactionServiceImpl(final AccountRepository accountRepository,
                                  final TransactionRepository transactionRepository,
                                  final IssuerRepository issuerRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.issuerRepository = issuerRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public TransactionEntity processTransaction(SendOrderDto orderRequest, String accountId) {
        AccountEntity account = this.accountRepository.findAccountById(accountId);
        OrderValidator.getInstance().validate(orderRequest, account);
        TransactionEntity transaction = createTransaction(account, orderRequest.getTimestamp());
        for (OperationDto operation : orderRequest.getOperations()) {
            createTransactionDetails(transaction, operation.getIssuer(), operation.getOperation(),
                    operation.getTotalShares(), operation.getSharePrice());
        }
        this.transactionRepository.commitTransaction(transaction);
        return transaction;
    }

    @Override
    public TransactionEntity createTransaction(final AccountEntity account, final Long timestamp) {
        return this.transactionRepository.createTransaction(account, timestamp);
    }

    @Override
    public TransactionDetailEntity createTransactionDetails(TransactionEntity transaction, String issuerName,
                                                            String operation, Long quantity, Double price) {
        IssuerEntity issuer = this.issuerRepository.findIssuerByName(issuerName);
        TransactionEnum transactionEnum = TransactionHelper.parseStringToEnum(operation);
        return this.transactionRepository.createTransactionDetail(transaction, issuer, transactionEnum, quantity, price);
    }
}
