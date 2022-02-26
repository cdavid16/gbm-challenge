package com.gbm.challenge.gbmchallenge.validator.impl;

import com.gbm.challenge.gbmchallenge.exception.business.InsufficientStocksException;
import com.gbm.challenge.gbmchallenge.model.entities.AccountStockEntity;
import com.gbm.challenge.gbmchallenge.model.request.OperationDto;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import com.gbm.challenge.gbmchallenge.validator.StockBalanceValidator;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.gbm.challenge.gbmchallenge.utils.AccountStockHelper.buildBalanceMap;

public class StockBalanceValidatorImpl implements StockBalanceValidator {

    private static final StockBalanceValidatorImpl INSTANCE = new StockBalanceValidatorImpl();

    private StockBalanceValidatorImpl() {}

    protected static StockBalanceValidator getInstance() { return INSTANCE; }

    @Override
    public void validateStockBalance(SendOrderDto orders, Set<AccountStockEntity> stocks) {
        final Map<String, AccountStockEntity> accountStocks = buildBalanceMap(stocks);
        for (OperationDto operation : orders.getOperations()) {
            validateStockOperation(accountStocks, operation);
        }
    }

    private void validateStockOperation(Map<String, AccountStockEntity> accountStocks, OperationDto operation) {
        final AccountStockEntity currentAccountStock = accountStocks.get(operation.getIssuer());
        final Long currentStock;
        if (Objects.isNull(currentAccountStock)) {
            currentStock = 0L;
        } else {
            currentStock = currentAccountStock.getQuantity();
        }

        if (currentStock.compareTo(operation.getTotalShares()) < 0) {
            throw new InsufficientStocksException();
        }
    }

}
