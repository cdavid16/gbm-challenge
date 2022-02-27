package com.gbm.challenge.gbmchallenge.utils;


import com.gbm.challenge.gbmchallenge.enums.OperationEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OperationHelperTest {

    @Test
    void computeAmountAppliedShouldThrowIllegalArgumentExceptionWhenTransactionIsNull() {
        Long totalShares = 1L;
        Double sharePrice = 0.0;
        assertThrows(IllegalArgumentException.class,
                () -> OperationHelper.computeAmountApplied(null, totalShares, sharePrice));
    }

    @Test
    void computeAmountAppliedShouldThrowIllegalArgumentExceptionWhenTotalSharesIsNull() {
        OperationEnum transaction = OperationEnum.SELL;
        Long totalShares = null;
        Double sharePrice = 0.0;
        assertThrows(IllegalArgumentException.class,
                () -> OperationHelper.computeAmountApplied(transaction, totalShares, sharePrice));
    }

    @Test
    void computeAmountAppliedShouldThrowIllegalArgumentExceptionWhenSharePriceIsNull() {
        OperationEnum transaction = OperationEnum.SELL;
        Long totalShares = 1L;
        Double sharePrice = null;
        assertThrows(IllegalArgumentException.class,
                () -> OperationHelper.computeAmountApplied(transaction, totalShares, sharePrice));
    }

    @Test
    void computeAmountAppliedShouldThrowIllegalArgumentExceptionWhenTotalSharesIsNegative() {
        OperationEnum transaction = OperationEnum.SELL;
        Long totalShares = -1L;
        Double sharePrice = 0.0;
        assertThrows(IllegalArgumentException.class,
                () -> OperationHelper.computeAmountApplied(transaction, totalShares, sharePrice));
    }

    @Test
    void computeAmountAppliedShouldThrowIllegalArgumentExceptionWhenSharePriceIsNegative() {
        OperationEnum transaction = OperationEnum.SELL;
        Long totalShares = 1L;
        Double sharePrice = -1.0;
        assertThrows(IllegalArgumentException.class,
                () -> OperationHelper.computeAmountApplied(transaction, totalShares, sharePrice));
    }

    @Test
    void computeAmountAppliedShouldComputeAmountToBeAppliedForSell() {
        OperationEnum transaction = OperationEnum.SELL;
        Long totalShares = 12L;
        Double sharePrice = 57.0;
        Double amount = OperationHelper.computeAmountApplied(transaction, totalShares, sharePrice);
        assertEquals(684.0, amount);
    }

    @Test
    void computeAmountAppliedShouldComputeAmountToBeAppliedForBuy() {
        OperationEnum transaction = OperationEnum.BUY;
        Long totalShares = 12L;
        Double sharePrice = 57.0;
        Double amount = OperationHelper.computeAmountApplied(transaction, totalShares, sharePrice);
        assertEquals(-684.0, amount);
    }
}