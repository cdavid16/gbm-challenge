package com.gbm.challenge.gbmchallenge.mockdata;

import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountData {

    public static final String ACCOUNT_ID_DUMMY = "1";

    public static AccountEntity getDummyAccount() {
        return new AccountEntity(ACCOUNT_ID_DUMMY, 1000.0,
                AccountStockData.getInstance().getTransactionDetails(), TransactionData.getDummyTransactions());
    }
}
