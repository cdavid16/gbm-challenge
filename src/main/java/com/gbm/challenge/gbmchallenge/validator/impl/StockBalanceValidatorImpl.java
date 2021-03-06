package com.gbm.challenge.gbmchallenge.validator.impl;

import com.gbm.challenge.gbmchallenge.enums.OperationEnum;
import com.gbm.challenge.gbmchallenge.exception.business.InsufficientStocksException;
import com.gbm.challenge.gbmchallenge.model.entities.AccountStockEntity;
import com.gbm.challenge.gbmchallenge.model.request.OperationDto;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import com.gbm.challenge.gbmchallenge.utils.OperationEnumHelper;
import com.gbm.challenge.gbmchallenge.validator.StockBalanceValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.util.Validate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static com.gbm.challenge.gbmchallenge.utils.AccountStockHelper.buildBalanceMap;

@Slf4j
@Component
public class StockBalanceValidatorImpl implements StockBalanceValidator {

    @Override
    public boolean validateStockBalance(SendOrderDto orders, Set<AccountStockEntity> stocks) {
        Validate.notNull(orders);
        Validate.notNull(stocks);
        final Map<String, AccountStockEntity> accountStocks = buildBalanceMap(stocks);
        orders.getOperations().forEach(operation -> validateStockOperation(accountStocks, operation));
        log.info("Order has passed stock balance validation.");
        return true;
    }

    private void validateStockOperation(Map<String, AccountStockEntity> accountStocks, OperationDto operation) {
        final AccountStockEntity currentAccountStock = accountStocks.get(operation.getIssuer());
        final Long currentStock;
        if (Objects.isNull(currentAccountStock)) {
            currentStock = 0L;
        } else {
            currentStock = currentAccountStock.getQuantity();
        }
        OperationEnum operationEnum = OperationEnumHelper.parseStringToEnum(operation.getOperation());
        if (currentStock.compareTo(operation.getTotalShares() * operationEnum.getBalanceFactor()) < 0) {
            throw new InsufficientStocksException();
        }
    }

}
