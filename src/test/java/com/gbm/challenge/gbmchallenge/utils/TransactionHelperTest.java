package com.gbm.challenge.gbmchallenge.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TransactionHelperTest {

    @Test
    public void shouldBeTrueWhenTransactionIsBuy() {
        var transactionType = "BUY";
        var actual = TransactionHelper.isValid(transactionType);
        Assertions.assertTrue(actual);
    }

    @Test
    public void shouldBeTrueWhenTransactionIsSell() {
        var transactionType = "SELL";
        var actual = TransactionHelper.isValid(transactionType);
        Assertions.assertTrue(actual);
    }

    @Test
    public void shouldBeFalseWhenTransactionNameIsValidButCaseSensitive() {
        var transactionType = "buy";
        var actual = TransactionHelper.isValid(transactionType);
        Assertions.assertFalse(actual);
    }

    @Test
    public void shouldBeFalseWhenTransactionTypeIsInvalid() {
        var transactionType = "FAKE_TRANSACTION";
        var actual = TransactionHelper.isValid(transactionType);
        Assertions.assertFalse(actual);
    }

}