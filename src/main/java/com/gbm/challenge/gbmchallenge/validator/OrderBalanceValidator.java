package com.gbm.challenge.gbmchallenge.validator;

import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;

@FunctionalInterface
public interface OrderBalanceValidator {

    boolean validateBalance(SendOrderDto orders, Double currentBalance);

}
