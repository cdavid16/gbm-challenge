package com.gbm.challenge.gbmchallenge.utils;

import com.gbm.challenge.gbmchallenge.enums.OperationEnum;
import com.gbm.challenge.gbmchallenge.model.entities.AccountStockEntity;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@UtilityClass
public class AccountStockHelper {

    private static final Predicate<AccountStockEntity> FIND_ALL = accountStockEntity -> true;

    public static Map<String, AccountStockEntity> buildBalanceMap(Set<AccountStockEntity> stocks) {
        return buildBalanceMap(stocks, FIND_ALL);
    }

    public static Map<String, AccountStockEntity> buildBalanceMap(Set<AccountStockEntity> stocks,
                                                    Predicate<AccountStockEntity> filter) {
        return stocks
                .stream()
                .filter(filter)
                .collect(Collectors.<AccountStockEntity, String, AccountStockEntity>toMap
                        (km-> km.getIssuer().getName(), accountStockEntity -> accountStockEntity))
                ;
    }

    public static AccountStockEntity updateStockBalance(OperationEnum operation, AccountStockEntity currentPosition,
                                                        Long transactionShares) {
        Long currentShares = currentPosition.getQuantity() + (transactionShares * operation.getBalanceFactor());
        currentPosition.setQuantity(currentShares);
        return currentPosition;
    }

}
