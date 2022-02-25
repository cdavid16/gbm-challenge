package com.gbm.challenge.gbmchallenge.validator.impl;

import com.gbm.challenge.gbmchallenge.exception.business.InsufficientStocksException;
import com.gbm.challenge.gbmchallenge.model.entities.AccountStockEntity;
import com.gbm.challenge.gbmchallenge.model.request.OperationDto;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import com.gbm.challenge.gbmchallenge.validator.StockBalanceValidator;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StockBalanceValidatorImpl implements StockBalanceValidator {

    private static final StockBalanceValidatorImpl INSTANCE = new StockBalanceValidatorImpl();

    private StockBalanceValidatorImpl() {}

    protected static StockBalanceValidator getInstance() { return INSTANCE; }

    @Override
    public void validateStockBalance(SendOrderDto orders, Set<AccountStockEntity> stocks) {
        final Map<String, Long> balance = buildBalanceMap(stocks);
        for (OperationDto operation : orders.getOperations()) {
            validateStockOperation(balance, operation);
        }
    }

    private Map<String, Long> buildBalanceMap(Set<AccountStockEntity> stocks) {
        return stocks.stream().collect(
                Collectors.<AccountStockEntity, String, Long>toMap(km-> km.getIssuer().getName(),
                        AccountStockEntity::getQuantity));
    }

    private void validateStockOperation(Map<String, Long> balance, OperationDto operation) {
        final Long currentStock = balance.getOrDefault(operation.getIssuer(), 0L);
        if (currentStock.compareTo(operation.getTotalShares()) < 0) {
            throw new InsufficientStocksException();
        }
    }

}
