package com.gbm.challenge.gbmchallenge.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OperationEnumHelperTest {

    @Test
    public void shouldBeTrueWhenTransactionIsBuy() {
        var transactionType = "BUY";
        var actual = OperationEnumHelper.isValid(transactionType);
        Assertions.assertTrue(actual);
    }

    @Test
    public void shouldBeTrueWhenTransactionIsSell() {
        var transactionType = "SELL";
        var actual = OperationEnumHelper.isValid(transactionType);
        Assertions.assertTrue(actual);
    }

    @Test
    public void shouldBeFalseWhenTransactionNameIsValidButCaseSensitive() {
        var transactionType = "buy";
        var actual = OperationEnumHelper.isValid(transactionType);
        Assertions.assertFalse(actual);
    }

    @Test
    public void shouldBeFalseWhenTransactionTypeIsInvalid() {
        var transactionType = "FAKE_TRANSACTION";
        var actual = OperationEnumHelper.isValid(transactionType);
        Assertions.assertFalse(actual);
    }

}