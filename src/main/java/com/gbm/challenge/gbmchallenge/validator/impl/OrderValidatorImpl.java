package com.gbm.challenge.gbmchallenge.validator.impl;

import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import com.gbm.challenge.gbmchallenge.validator.OrderValidator;


public class OrderValidatorImpl implements OrderValidator {

    private static final OrderValidator INSTANCE = new OrderValidatorImpl();

    public static OrderValidator getInstance() { return INSTANCE; }

    @Override
    public void validate(SendOrderDto orders, AccountEntity account) {
        MarketClosedValidatorImpl.getInstance().validateMarketOpen(orders.getTimestamp());
        StockBalanceValidatorImpl.getInstance().validateStockBalance(orders, account.getAccountStocks());
        OrderBalanceValidatorImpl.getInstance().validateBalance(orders, account.getBalance());
        DuplicateOperationValidatorImpl.getInstance().validateDuplicateOperation(orders, account.getTransactions());
    }
}
