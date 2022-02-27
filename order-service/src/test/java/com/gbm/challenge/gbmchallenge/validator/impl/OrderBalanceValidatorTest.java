package com.gbm.challenge.gbmchallenge.validator.impl;

import com.gbm.challenge.gbmchallenge.exception.business.InsufficientBalanceException;
import com.gbm.challenge.gbmchallenge.mockdata.SendOrderDtoValidData;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import com.gbm.challenge.gbmchallenge.validator.OrderBalanceValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderBalanceValidatorTest {

    private OrderBalanceValidator validator;

    @BeforeEach
    void setup() {
        validator = new OrderBalanceValidatorImpl();
    }

    @Test
    void validateBalanceShouldReturnTrueWhenOrderTotalIsLessThanCurrentBalance() {
        double currentBalance = 10000.0;
        SendOrderDto request = SendOrderDtoValidData.getSingleOrderBuy();

        boolean isValid = validator.validateBalance(request, currentBalance);

        assertTrue(isValid);
    }

    @Test
    void validateBalanceShouldThrowIllegalArgumentExceptionWhenOrdersIsNull() {
        double currentBalance = 10000.0;

        assertThrows(IllegalArgumentException.class, () -> validator.validateBalance(null, currentBalance));
    }

    @Test
    void validateBalanceShouldThrowIllegalArgumentExceptionWhenBalanaceIsNull() {
        SendOrderDto request = SendOrderDtoValidData.getSingleOrderBuy();

        assertThrows(IllegalArgumentException.class, () -> validator.validateBalance(request, null));
    }

    @Test
    void validateBalanceShouldThrowInsufficientBalanceExceptionWhenThereIsNoEnoughBalance() {
        SendOrderDto request = SendOrderDtoValidData.getSingleOrderOverpriced();
        double currentBalance = 1000.0;

        assertThrows(InsufficientBalanceException.class, () -> validator.validateBalance(request, currentBalance));
    }

    @Test
    void validateBalanceShouldReturnTrueWhenBalanceIsValid() {
        SendOrderDto request = SendOrderDtoValidData.getSingleOrderOverpriced();
        double currentBalance = 1000000.0;

        assertThrows(InsufficientBalanceException.class, () -> validator.validateBalance(request, currentBalance));
    }
}