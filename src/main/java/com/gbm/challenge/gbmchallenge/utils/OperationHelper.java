package com.gbm.challenge.gbmchallenge.utils;

import com.gbm.challenge.gbmchallenge.enums.OperationEnum;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OperationHelper {

    public static Double computeAmountApplied(final OperationEnum transaction, final Long totalShares,
                                        final Double sharePrice) {
        return totalShares * sharePrice * transaction.getBalanceFactor();
    }

}
