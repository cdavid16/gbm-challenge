package com.gbm.challenge.gbmchallenge.service.impl;

import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.repository.AccountRepository;
import com.gbm.challenge.gbmchallenge.service.AccountService;
import com.gbm.challenge.gbmchallenge.utils.BigDecimalHelper;
import org.apache.commons.lang.util.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    @Autowired
    public AccountServiceImpl(@Validated final AccountRepository repository) {
        this.repository = repository;
    }

    @Transactional(rollbackOn = Exception.class)
    public AccountEntity createAccount(@Validated final Double balance) {
        Validate.notNull(balance);
        BigDecimalHelper.isPositive(balance);
        return repository.createAccountAndFlush(balance);
    }

}
