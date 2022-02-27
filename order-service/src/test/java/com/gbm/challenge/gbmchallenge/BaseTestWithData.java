package com.gbm.challenge.gbmchallenge;

import com.gbm.challenge.gbmchallenge.mockdata.*;

public abstract class BaseTestWithData {

    protected AccountData accountData;
    protected IssuerData issuerData;
    protected TransactionData transactionData;
    protected TransactionDetailData transactionDetailData;
    protected AccountStockData accountStockData;

    public BaseTestWithData() {
        this.accountData = new AccountData();
        this.issuerData = new IssuerData();
        this.transactionData = new TransactionData(accountData);
        this.transactionDetailData = new TransactionDetailData(transactionData, issuerData);
        this.accountStockData = new AccountStockData(issuerData, accountData, transactionDetailData);
    }

}
