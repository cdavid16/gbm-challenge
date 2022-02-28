package com.gbm.challenge.gbmchallenge;

import com.gbm.challenge.gbmchallenge.mockdata.*;

public abstract class BaseTestWithData {

    protected final AccountData accountData;
    protected final IssuerData issuerData;
    protected final TransactionData transactionData;
    protected final TransactionDetailData transactionDetailData;
    protected final AccountStockData accountStockData;

    public BaseTestWithData() {
        this.accountData = new AccountData();
        this.issuerData = new IssuerData();
        this.transactionData = new TransactionData(accountData);
        this.transactionDetailData = new TransactionDetailData(transactionData, issuerData);
        this.accountStockData = new AccountStockData(issuerData, accountData, transactionDetailData);
    }

}
