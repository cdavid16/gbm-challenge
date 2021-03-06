package com.gbm.challenge.gbmchallenge.validator;

import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;

@FunctionalInterface
public interface OrderValidator {

    /**
     * This method validates whether the operation is valid or not.
     * @param orders Request which contains the orders that are going to be validated.
     * @param account Account which is trying to process the orders.
     */
    void validate(SendOrderDto orders, AccountEntity account);
}
