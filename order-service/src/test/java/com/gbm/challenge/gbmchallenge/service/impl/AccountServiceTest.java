package com.gbm.challenge.gbmchallenge.service.impl;

import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    private AccountRepository repository;
    private AccountServiceImpl service;

    @BeforeEach
    void init() {
        repository = mock(AccountRepository.class);
        service = new AccountServiceImpl(repository);
    }

    @Test
    public void shouldReturnEntityWhenBalanceIsNotNull() {
        Double balance = 10.0;

        service.createAccount(balance);

        verify(repository, times(1)).createAccountAndFlush(any());
    }

    @Test
    public void shouldThrownExceptionWhenBalanceIsNull() {
        Double balance = null;

        when(repository.saveAndFlush(any())).thenReturn(new AccountEntity());

        assertThrows(IllegalArgumentException.class, () -> service.createAccount(balance));
    }

    @Test
    public void shouldThrownExceptionWhenBalanceIsNegative() {
        Double balance = -1.0;

        when(repository.saveAndFlush(any())).thenReturn(new AccountEntity());

        assertThrows(IllegalArgumentException.class, () -> service.createAccount(balance));
    }

}