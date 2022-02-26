package com.gbm.challenge.gbmchallenge.mockdata;

import com.gbm.challenge.gbmchallenge.model.entities.TransactionEntity;
import lombok.experimental.UtilityClass;

import java.util.Set;

import static com.gbm.challenge.gbmchallenge.mockdata.FilterData.getTransactionDetailsByTransactionId;

@UtilityClass
public class TransactionData {

    public static final TransactionEntity TRANSACTION_1 = new TransactionEntity("1",
            1645813301000L, true,
            TransactionDetailData.getInstance().getTransactionDetails(getTransactionDetailsByTransactionId("1")),
            AccountData.getDummyAccount());

    public static final TransactionEntity TRANSACTION_2 = new TransactionEntity("2",
            1645806101000L, true,
            TransactionDetailData.getInstance().getTransactionDetails(getTransactionDetailsByTransactionId("2")),
            AccountData.getDummyAccount());

    public static final TransactionEntity TRANSACTION_3 = new TransactionEntity("3",
            1645719701000L, true,
            TransactionDetailData.getInstance().getTransactionDetails(getTransactionDetailsByTransactionId("3")),
            AccountData.getDummyAccount());

    public static TransactionEntity getDummyTransaction() {
        return TRANSACTION_1;
    }

    public static Set<TransactionEntity> getDummyTransactions() {
        return Set.of(
                TRANSACTION_1,
                TRANSACTION_2,
                TRANSACTION_3
        );
    }
}
