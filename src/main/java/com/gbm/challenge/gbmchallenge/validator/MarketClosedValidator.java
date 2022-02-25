package com.gbm.challenge.gbmchallenge.validator;

@FunctionalInterface
public interface MarketClosedValidator {

    /**
     * This method validates whether the market is open when the order is created
     * @param timestamp Long representation of Timestamp of the order in UTC
     */
    void validateMarketOpen(Long timestamp);

}
