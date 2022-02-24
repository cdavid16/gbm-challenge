package com.gbm.challenge.gbmchallenge.service;

import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;

public interface AccountService {
    AccountEntity createAccount(final Double balance);
}
