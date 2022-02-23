package com.gbm.challenge.gbmchallenge.service.impl;

import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.repository.AccountRepository;
import com.gbm.challenge.gbmchallenge.service.IAccountService;
import com.gbm.challenge.gbmchallenge.utils.BigDecimalHelper;
import org.apache.commons.lang.util.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Service
public class AccountService implements IAccountService {

    private final AccountRepository repository;

    @Autowired
    public AccountService(@Validated final AccountRepository repository) {
        this.repository = repository;
    }

    @Transactional(rollbackOn = Exception.class)
    public AccountEntity createAccount(@Validated @DecimalMin("0.0") final BigDecimal balance) {
        Validate.notNull(balance);
        BigDecimalHelper.isPositive(balance);
        AccountEntity account = new AccountEntity(balance);
        return repository.saveAndFlush(account);
    }

}
