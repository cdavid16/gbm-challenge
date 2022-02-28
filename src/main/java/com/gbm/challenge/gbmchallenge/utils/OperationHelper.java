package com.gbm.challenge.gbmchallenge.utils;

import com.gbm.challenge.gbmchallenge.enums.OperationEnum;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang.util.Validate;

@UtilityClass
public class OperationHelper {

    public static Double computeAmountApplied(final OperationEnum transaction, final Long totalShares,
                                        final Double sharePrice) {
        Validate.notNull(transaction);
        NumberHelper.isPositive(totalShares);
        NumberHelper.isPositive(sharePrice);
        return totalShares * sharePrice * transaction.getBalanceFactor();
    }

}
