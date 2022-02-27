package com.gbm.challenge.gbmchallenge.utils;

import com.gbm.challenge.gbmchallenge.enums.OperationEnum;
import com.gbm.challenge.gbmchallenge.exception.business.InsufficientStocksException;
import com.gbm.challenge.gbmchallenge.model.entities.AccountStockEntity;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang.util.Validate;

import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@UtilityClass
public class AccountStockHelper {

    private static final Predicate<AccountStockEntity> FIND_ALL = accountStockEntity -> true;

    public static Map<String, AccountStockEntity> buildBalanceMap(final Set<AccountStockEntity> stocks) {
        return buildBalanceMap(stocks, FIND_ALL);
    }

    public static Map<String, AccountStockEntity> buildBalanceMap(final Set<AccountStockEntity> stocks,
                                                    final Predicate<AccountStockEntity> filter) {
        Validate.notNull(stocks);
        Validate.notNull(filter);
        return stocks
                .stream()
                .filter(filter)
                .collect(Collectors.<AccountStockEntity, String, AccountStockEntity>toMap
                        (km-> km.getIssuer().getName(), accountStockEntity -> accountStockEntity))
                ;
    }

    public static AccountStockEntity updateStockBalance(final OperationEnum operation,
                                                        final AccountStockEntity currentPosition,
                                                        final Long transactionShares) {
        NumberHelper.isPositive(transactionShares);
        Validate.notNull(currentPosition, "Current Position cannot be null");
        NumberHelper.isPositive(currentPosition.getQuantity());
        Validate.notNull(operation, "Operation cannot be null");
        long currentShares = currentPosition.getQuantity() + (transactionShares * operation.getStockFactor());
        if (currentShares < 0) {
            throw new InsufficientStocksException();
        }
        currentPosition.setQuantity(Math.abs(currentShares));
        return currentPosition;
    }

}
