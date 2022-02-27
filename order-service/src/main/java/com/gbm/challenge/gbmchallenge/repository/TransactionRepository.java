package com.gbm.challenge.gbmchallenge.repository;

import com.gbm.challenge.gbmchallenge.enums.OperationEnum;
import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.model.entities.IssuerEntity;
import com.gbm.challenge.gbmchallenge.model.entities.TransactionDetailEntity;
import com.gbm.challenge.gbmchallenge.model.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {

    default TransactionEntity createTransaction(final AccountEntity account, final Long timestamp) {
        return new TransactionEntity(account, timestamp);
    }

    default void createTransactionDetail(final TransactionEntity transaction,
                                                            final IssuerEntity issuer,
                                                            final OperationEnum operation,
                                                            final Long quantity,
                                                            final Double price) {
        TransactionDetailEntity transactionDetail = new TransactionDetailEntity(transaction, issuer, operation,
                                            quantity, price);
        transaction.getTransactionDetails().add(transactionDetail);
        issuer.getTransactionDetails().add(transactionDetail);
    }

}
