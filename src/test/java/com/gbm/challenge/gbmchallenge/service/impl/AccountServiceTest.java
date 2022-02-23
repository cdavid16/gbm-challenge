package com.gbm.challenge.gbmchallenge.service.impl;

import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    private AccountRepository repository;
    private AccountService service;

    @BeforeEach
    void init() {
        repository = mock(AccountRepository.class);
        service = new AccountService(repository);
    }

    @Test
    public void shouldReturnEntityWhenBalanceIsNotNull() {
        BigDecimal balance = new BigDecimal("10");

        service.createAccount(balance);

        verify(repository, times(1)).saveAndFlush(any());
    }

    @Test
    public void shouldThrownExceptionWhenBalanceIsNull() {
        BigDecimal balance = null;

        when(repository.saveAndFlush(any())).thenReturn(new AccountEntity());

        assertThrows(IllegalArgumentException.class, () -> service.createAccount(balance));
    }

    @Test
    public void shouldThrownExceptionWhenBalanceIsNegative() {
        BigDecimal balance = new BigDecimal("-1");

        when(repository.saveAndFlush(any())).thenReturn(new AccountEntity());

        assertThrows(IllegalArgumentException.class, () -> service.createAccount(balance));
    }

}