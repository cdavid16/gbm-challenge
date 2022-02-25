package com.gbm.challenge.gbmchallenge.repository;

import com.gbm.challenge.gbmchallenge.enums.TransactionEnum;
import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.model.entities.IssuerEntity;
import com.gbm.challenge.gbmchallenge.model.entities.TransactionDetailEntity;
import com.gbm.challenge.gbmchallenge.model.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {

    default TransactionEntity createTransaction(AccountEntity account, Long timestamp) {
        return new TransactionEntity(account, timestamp);
    }

    default TransactionDetailEntity createTransactionDetail(TransactionEntity transaction, IssuerEntity issuer,
                                                            TransactionEnum operation, Long quantity, Double price) {
        TransactionDetailEntity transactionDetail = new TransactionDetailEntity(transaction, issuer, operation,
                                            quantity, price);
        transaction.getTransactionDetails().add(transactionDetail);
        issuer.getTransactionDetails().add(transactionDetail);
        return transactionDetail;
    }

    default TransactionEntity commitTransaction(TransactionEntity transaction) {
        return saveAndFlush(transaction);
    }

}
