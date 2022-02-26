package com.gbm.challenge.gbmchallenge.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang.util.Validate;
import org.apache.commons.validator.routines.BigDecimalValidator;

import java.math.BigDecimal;

@UtilityClass
public class BigDecimalHelper {

    private static final BigDecimalValidator VALIDATOR = BigDecimalValidator.getInstance();

    public static void isPositive(Double value) {
        Validate.notNull(value);
        if(!VALIDATOR.isInRange(value, 0, Long.MAX_VALUE)) {
            throw new IllegalArgumentException("Given BigDecimal cannot be less than zero");
        }
    }

}
