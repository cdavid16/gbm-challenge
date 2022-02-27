package com.gbm.challenge.gbmchallenge.service.impl;

import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.repository.AccountRepository;
import com.gbm.challenge.gbmchallenge.service.AccountService;
import com.gbm.challenge.gbmchallenge.utils.NumberHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    @Autowired
    public AccountServiceImpl(@Validated final AccountRepository repository) {
        this.repository = repository;
    }

    @Transactional(rollbackOn = Exception.class)
    public AccountEntity createAccount(@Validated final Double balance) {
        log.info("Account is trying to be created with balance {}", balance);
        NumberHelper.isPositive(balance);
        AccountEntity account = repository.createAccountAndFlush(balance);
        log.info("Account created successfully {}", account);
        return account;
    }

}
