package com.gbm.challenge.gbmchallenge.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.util.Validate;
import org.apache.commons.validator.routines.BigDecimalValidator;

@UtilityClass
public class NumberHelper {

    private static final BigDecimalValidator VALIDATOR = BigDecimalValidator.getInstance();

    public static boolean isPositive(Double value) {
        Validate.notNull(value);
        if(!VALIDATOR.isInRange(value, 0, Double.MAX_VALUE)) {
            throw new IllegalArgumentException("Given BigDecimal cannot be less than zero");
        }
        return true;
    }

    public static boolean isPositive(Long value) {
        Validate.notNull(value);
        if(!VALIDATOR.isInRange(value, 0, Long.MAX_VALUE)) {
            throw new IllegalArgumentException("Given number cannot be negative");
        }
        return true;
    }

    public static boolean isNumeric(String str) {
        if (!NumberUtils.isNumber(str)) {
            throw new IllegalArgumentException("Given input is not a number.");
        }
        return true;
    }
}
