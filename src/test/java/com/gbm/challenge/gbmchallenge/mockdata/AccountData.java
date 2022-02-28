package com.gbm.challenge.gbmchallenge.mockdata;

import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;

public class AccountData {

    public static final String ACCOUNT_ID_DUMMY = "1";

    private final AccountEntity dummyAccount;

    public AccountData() {
        dummyAccount = new AccountEntity(ACCOUNT_ID_DUMMY, 1000.0, null, null);
    }

    public AccountEntity getDummyAccount() {
        return dummyAccount;
    }
}
