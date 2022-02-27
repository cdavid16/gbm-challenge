package com.gbm.challenge.gbmchallenge.validator.impl;

import com.gbm.challenge.gbmchallenge.BaseTestWithData;
import com.gbm.challenge.gbmchallenge.exception.business.InsufficientStocksException;
import com.gbm.challenge.gbmchallenge.mockdata.*;
import com.gbm.challenge.gbmchallenge.model.entities.AccountStockEntity;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import com.gbm.challenge.gbmchallenge.validator.StockBalanceValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StockBalanceValidatorTest extends BaseTestWithData {

    private StockBalanceValidator validator;

    @BeforeEach
    void setup() {
        validator = new StockBalanceValidatorImpl();
    }

    @Test
    void validateStockBalanceShouldThrowIllegalArgumentExceptionWhenOrdersIsNull() {
        assertThrows(IllegalArgumentException.class, ()-> validator.validateStockBalance(null, Set.of()));
    }

    @Test
    void validateStockBalanceShouldThrowIllegalArgumentExceptionWhenStocksIsNull() {
        SendOrderDto request = SendOrderDtoValidData.getSingleOrderBuy();
        assertThrows(IllegalArgumentException.class, ()-> validator.validateStockBalance(request, null));
    }

    @Test
    void validateStockBalanceShouldReturnTrueWhenOrderIsBuy() {
        SendOrderDto request = SendOrderDtoValidData.getSingleOrderBuy();
        Set<AccountStockEntity> stocks = this.accountStockData.getDetails();
        var actual = validator.validateStockBalance(request, stocks);
        assertTrue(actual);
    }

    @Test
    void validateStockBalanceShouldReturnTrueWhenOrderIsSell() {
        SendOrderDto request = SendOrderDtoValidData.getSingleOrderSell();
        Set<AccountStockEntity> stocks = this.accountStockData.getDetails();
        var actual = validator.validateStockBalance(request, stocks);
        assertTrue(actual);
    }

    @Test
    void validateStockBalanceShouldThrowInsufficientStocksExceptionWhenOrderIsSell() {
        SendOrderDto request = SendOrderDtoValidData.getSingleOrderOverQuantity();
        Set<AccountStockEntity> stocks = this.accountStockData.getDetails();
        assertThrows(InsufficientStocksException.class, ()-> validator.validateStockBalance(request, stocks));

    }
}