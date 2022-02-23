package com.gbm.challenge.gbmchallenge.service;

import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;

import java.math.BigDecimal;

public interface IAccountService {
    AccountEntity createAccount(final BigDecimal balance);
}
