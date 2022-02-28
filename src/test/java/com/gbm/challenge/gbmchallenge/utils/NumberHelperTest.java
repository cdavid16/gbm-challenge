package com.gbm.challenge.gbmchallenge.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberHelperTest {

    @Test
    void isPositiveShouldThrowIllegalArgumentExceptionWhenDoubleValueIsNull() {
        Double value = null;
        assertThrows(IllegalArgumentException.class, () -> NumberHelper.isPositive(value));
    }

    @Test
    void isPositiveShouldThrowIllegalArgumentExceptionWhenDoubleValueIsNegative() {
        Double value = -1.0;
        assertThrows(IllegalArgumentException.class, () -> NumberHelper.isPositive(value));
    }

    @Test
    void isPositiveShouldThrowIllegalArgumentExceptionWhenLongValueIsNull() {
        Long value = null;
        assertThrows(IllegalArgumentException.class, () -> NumberHelper.isPositive(value));
    }

    @Test
    void isPositiveShouldThrowIllegalArgumentExceptionWhenLongValueIsNegative() {
        Long value = -1L;
        assertThrows(IllegalArgumentException.class, () -> NumberHelper.isPositive(value));
    }

    @Test
    void isPositiveShouldReturnTrueWhenDoubleIsPositive() {
        Double value = 1.0;
        assertTrue(NumberHelper.isPositive(value));
    }

    @Test
    void isPositiveShouldReturnTrueWhenLongIsPositive() {
        Long value = 1L;
        assertTrue(NumberHelper.isPositive(value));
    }

    @Test
    void isNumericShouldReturnTrueWhenStringIsPositiveNumber() {
        String value = "123";
        assertTrue(NumberHelper.isNumeric(value));
    }

    @Test
    void isNumericShouldReturnTrueWhenStringIsNegativeNumber() {
        String value = "-123";
        assertTrue(NumberHelper.isNumeric(value));
    }

    @Test
    void isNumericShouldThrowIllegalArgumentExceptionWhenStringIsNull() {
        assertThrows(IllegalArgumentException.class, () -> NumberHelper.isNumeric(null));
    }

    @Test
    void isNumericShouldThrowIllegalArgumentExceptionWhenStringIsNotNumeric() {
        String value = "abc";
        assertThrows(IllegalArgumentException.class, () -> NumberHelper.isNumeric(value));
    }
}