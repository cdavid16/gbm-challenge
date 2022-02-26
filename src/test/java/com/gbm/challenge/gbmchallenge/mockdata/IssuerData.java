package com.gbm.challenge.gbmchallenge.mockdata;

import com.gbm.challenge.gbmchallenge.model.entities.IssuerEntity;
import lombok.experimental.UtilityClass;

import static com.gbm.challenge.gbmchallenge.mockdata.FilterData.getAccountStockByIssuerId;
import static com.gbm.challenge.gbmchallenge.mockdata.FilterData.getTransactionDetailsByIssuerId;

@UtilityClass
public class IssuerData {

    public static final IssuerEntity AAPL = new IssuerEntity("1", "AAPL",
            TransactionDetailData.getInstance().getTransactionDetails(getTransactionDetailsByIssuerId("1")),
            AccountStockData.getInstance().getTransactionDetails(getAccountStockByIssuerId("3"))
    );

    public static final IssuerEntity AMZN = new IssuerEntity("2", "AMZN",
            TransactionDetailData.getInstance().getTransactionDetails(getTransactionDetailsByIssuerId("2")),
            AccountStockData.getInstance().getTransactionDetails(getAccountStockByIssuerId("3"))
    );

    public static final IssuerEntity NFTX = new IssuerEntity("3", "NFTX",
            TransactionDetailData.getInstance().getTransactionDetails(getTransactionDetailsByIssuerId("3")),
            AccountStockData.getInstance().getTransactionDetails(getAccountStockByIssuerId("3"))
    );
}
