package com.gbm.challenge.gbmchallenge.validator.impl;

import com.gbm.challenge.gbmchallenge.BaseTestWithData;
import com.gbm.challenge.gbmchallenge.exception.business.DuplicateOperationException;
import com.gbm.challenge.gbmchallenge.mockdata.SendOrderDtoInvalidData;
import com.gbm.challenge.gbmchallenge.mockdata.SendOrderDtoValidData;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import com.gbm.challenge.gbmchallenge.validator.DuplicateOperationValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DuplicateOperationValidatorImplTest extends BaseTestWithData {

    private DuplicateOperationValidator validator;

    @BeforeEach
    void setup() {
        validator = new DuplicateOperationValidatorImpl();
    }

    @Test
    void validateDuplicateOperationShouldThrowIllegalArgumentExceptionWhenOrdersIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateDuplicateOperation(null, Set.of()));
    }

    @Test
    void validateDuplicateOperationShouldThrowIllegalArgumentExceptionWhenTransactionsIsNull() {
        SendOrderDto order = SendOrderDtoValidData.getSingleOrderBuy();
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateDuplicateOperation(order, null));
    }

    @Test
    void validateDuplicateOperationShouldThrowIllegalArgumentExceptionWhenOrdersTimestampIsNull() {
        SendOrderDto order = SendOrderDtoInvalidData.getOrderWithNullTimestamp();
        assertThrows(IllegalArgumentException.class,
                () -> validator.validateDuplicateOperation(order, Set.of()));
    }

    @Test
    void validateDuplicateOperationShouldThrowDuplicateOperationExceptionWhenTransactionsIs4MinsBeforeExisting() {
        SendOrderDto order = SendOrderDtoValidData.getSingleOrderBeforeFourMinutesBuy();
        assertThrows(DuplicateOperationException.class,
                () -> validator.validateDuplicateOperation(order, transactionData.getDummyTransactions()));
    }

    @Test
    void validateDuplicateOperationShouldThrowDuplicateOperationExceptionWhenTransactionsIs4MinsAfterExisting() {
        SendOrderDto order = SendOrderDtoValidData.getSingleOrderAfterFourMinutesBuy();
        assertThrows(DuplicateOperationException.class,
                () -> validator.validateDuplicateOperation(order, transactionData.getDummyTransactions()));
    }

    @Test
    void validateDuplicateOperationShouldSucceedWhenTransactionsIsDifferent4MinsBeforeExisting() {
        SendOrderDto order = SendOrderDtoValidData.getSingleOrderAfterFourMinutesBuyDifferent();
        var actual = validator.validateDuplicateOperation(order, transactionData.getDummyTransactions());
        assertTrue(actual);
    }

    @Test
    void validateDuplicateOperationShouldSucceedWhenTransactionsIsSameQuantityButDifferentOperationInRange() {
        SendOrderDto order = SendOrderDtoValidData.getSingleOrderBeforeFourMinutesSell();
        var actual = validator.validateDuplicateOperation(order, transactionData.getDummyTransactions());
        assertTrue(actual);
    }

    @Test
    void validateDuplicateOperationShouldSucceedWhenTransactionsIs6MinsAfterExisting() {
        SendOrderDto order = SendOrderDtoValidData.getSingleOrderBuyAfterSixMins();
        var actual = validator.validateDuplicateOperation(order, transactionData.getDummyTransactions());
        assertTrue(actual);
    }

    @Test
    void validateDuplicateOperationShouldSucceedWhenTransactionsIs6MinsBeforeExisting() {
        SendOrderDto order = SendOrderDtoValidData.getSingleOrderBuyBeforeSixMins();
        var actual = validator.validateDuplicateOperation(order, transactionData.getDummyTransactions());
        assertTrue(actual);
    }

}