package com.gbm.challenge.gbmchallenge.validator;

import com.gbm.challenge.gbmchallenge.model.entities.AccountStockEntity;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;

import java.util.Set;

@FunctionalInterface
public interface StockBalanceValidator {

    /**
     * This method validates whether the position can be open or not.
     * @param orders Request which contains the orders that are going to be validated.
     * @param stocks Shares that the account has assigned.
     */
    boolean validateStockBalance(SendOrderDto orders, Set<AccountStockEntity> stocks);

}
