package com.gbm.challenge.gbmchallenge.mockdata;

import com.gbm.challenge.gbmchallenge.model.entities.TransactionDetailEntity;

import java.util.Set;

import static com.gbm.challenge.gbmchallenge.enums.OperationEnum.BUY;
import static com.gbm.challenge.gbmchallenge.enums.OperationEnum.SELL;
import static com.gbm.challenge.gbmchallenge.mockdata.IssuerData.*;
import static com.gbm.challenge.gbmchallenge.mockdata.TransactionData.*;

public class TransactionDetailData extends BaseQueryableDummy<TransactionDetailEntity> {

    public static final TransactionDetailData INSTANCE = new TransactionDetailData();

    private TransactionDetailData(){}

    public static TransactionDetailData getInstance() {
        return INSTANCE;
    }

    public TransactionDetailEntity getTransactionDetail() {
        return new TransactionDetailEntity();
    }

    @Override
    public Set<TransactionDetailEntity> getTransactionDetails() {
        return Set.of(
                new TransactionDetailEntity(TRANSACTION_1, AMZN, BUY, 10L, 1010.50),
                new TransactionDetailEntity(TRANSACTION_1, NFTX, BUY, 15L, 950.00),
                new TransactionDetailEntity(TRANSACTION_1, AMZN, BUY, 100L, 535.00),
                new TransactionDetailEntity(TRANSACTION_2, AMZN, SELL, 30L, 600.00),
                new TransactionDetailEntity(TRANSACTION_2, AAPL, BUY, 5L, 2000.00),
                new TransactionDetailEntity(TRANSACTION_2, NFTX, BUY, 20L, 935.00),
                new TransactionDetailEntity(TRANSACTION_3, NFTX, SELL, 35L, 1100.00),
                new TransactionDetailEntity(TRANSACTION_3, AMZN, SELL, 5L, 1500.00),
                new TransactionDetailEntity(TRANSACTION_3, AAPL, BUY, 5L, 1900.00)
        );
    }

}
