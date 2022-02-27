package com.gbm.challenge.gbmchallenge.mockdata;

import com.gbm.challenge.gbmchallenge.model.entities.TransactionEntity;

import java.util.Set;

public class TransactionData {

    private final AccountData accountData;
    public final TransactionEntity TRANSACTION_1;
    public final TransactionEntity TRANSACTION_2;
    public final TransactionEntity TRANSACTION_3;
    private final Set<TransactionEntity> transactionEntities;

    public TransactionData(final AccountData accountData) {
        this.accountData = accountData;
        TRANSACTION_1 = new TransactionEntity("1",
                1645813301000L, true,
                null,
                accountData.getDummyAccount());
        TRANSACTION_2 = new TransactionEntity("2",
                1645806101000L, true,
                null,
                accountData.getDummyAccount());
        TRANSACTION_3 = new TransactionEntity("3",
                1645719701000L, true,
                null,
                accountData.getDummyAccount());
        transactionEntities = Set.of(TRANSACTION_1, TRANSACTION_2, TRANSACTION_3);
    }

    public TransactionEntity getDummyTransaction() {
        return TRANSACTION_1;
    }

    public Set<TransactionEntity> getDummyTransactions() {
        return transactionEntities;
    }
}
