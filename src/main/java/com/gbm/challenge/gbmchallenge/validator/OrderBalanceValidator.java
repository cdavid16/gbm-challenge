package com.gbm.challenge.gbmchallenge.validator;

import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;

import java.math.BigDecimal;

@FunctionalInterface
public interface OrderBalanceValidator {

    void validateBalance(SendOrderDto orders, Double currentBalance);

}
