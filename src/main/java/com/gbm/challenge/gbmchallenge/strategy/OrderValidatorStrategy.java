package com.gbm.challenge.gbmchallenge.strategy;

import com.gbm.challenge.gbmchallenge.validator.OrderValidator;

@FunctionalInterface
public interface OrderValidatorStrategy {

    OrderValidator getValidator();

}
