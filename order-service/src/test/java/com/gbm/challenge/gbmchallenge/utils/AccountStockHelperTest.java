package com.gbm.challenge.gbmchallenge.utils;

import com.gbm.challenge.gbmchallenge.BaseTestWithData;
import com.gbm.challenge.gbmchallenge.enums.OperationEnum;
import com.gbm.challenge.gbmchallenge.exception.business.InsufficientStocksException;
import com.gbm.challenge.gbmchallenge.mockdata.AccountStockData;
import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.model.entities.AccountStockEntity;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static com.gbm.challenge.gbmchallenge.utils.AccountStockHelper.buildBalanceMap;
import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AccountStockHelperTest extends BaseTestWithData {

    @Test
    void buildBalanceMapShouldThrowInvalidArgumentExceptionWhenStocksIsNullAndThereIsNoFilter() {
        assertThrows(IllegalArgumentException.class, ()-> buildBalanceMap(null));
    }

    @Test
    void buildBalanceMapShouldReturnAllTransactionsAsMapWhenNoFilterIsPassed() {
        Set<AccountStockEntity> accountStocks = accountStockData.getDetails();
        AccountEntity account = accountData.getDummyAccount();
        Map<String, AccountStockEntity> expected = Map.ofEntries(
                entry("AMZN", new AccountStockEntity(75L, account, issuerData.AMZN)),
                entry("NFTX", new AccountStockEntity(0L,account, issuerData.NFTX)),
                entry("AAPL", new AccountStockEntity(10L, account, issuerData.AAPL))
        );

        Map<String, AccountStockEntity> actual = buildBalanceMap(accountStocks);

        assertEquals(expected, actual);
    }

    @Test
    void buildBalanceMapShouldSucceedWhenNoFilterIsPassedAndSetIsEmpty() {
        Set<AccountStockEntity> accountStocks = Set.of();
        Map<String, AccountStockEntity> expected = Map.of();

        Map<String, AccountStockEntity> actual = buildBalanceMap(accountStocks);

        assertEquals(expected, actual);
    }

    @Test
    void buildBalanceMapShouldSucceedWhenFilterIsPassedButRemoveAllResults() {
        Set<AccountStockEntity> accountStocks = accountStockData.getDetails();
        Map<String, AccountStockEntity> expected = Map.of();

        Map<String, AccountStockEntity> actual = buildBalanceMap(accountStocks, x-> false);

        assertEquals(expected, actual);
    }

    @Test
    void updateStockBalanceShouldUpdateStockBalanceWhenBuyingStocks() {
        Long quantity = 1L;
        OperationEnum operationEnum = OperationEnum.BUY;
        var currentPosition = AccountStockData.getDummy();

        AccountStockHelper.updateStockBalance(operationEnum, currentPosition, quantity);
        assertEquals(11, currentPosition.getQuantity());
    }

    @Test
    void updateStockBalanceShouldUpdateStockBalanceWhenSellingStocks() {
        Long quantity = 1L;
        OperationEnum operationEnum = OperationEnum.SELL;
        var currentPosition = AccountStockData.getDummy();

        AccountStockHelper.updateStockBalance(operationEnum, currentPosition, quantity);

        assertEquals(9, currentPosition.getQuantity());
    }

    @Test
    void updateStockBalanceShouldThrowInsufficientStockException() {
        Long quantity = 100L;
        OperationEnum operationEnum = OperationEnum.SELL;
        var currentPosition = AccountStockData.getDummy();

        assertThrows(InsufficientStocksException.class, () ->
                AccountStockHelper.updateStockBalance(operationEnum, currentPosition, quantity));
    }

    @Test
    void updateStockBalanceShouldThrowIllegalArgumentExceptionWhenQuantityIsNull() {
        OperationEnum operationEnum = OperationEnum.SELL;
        var currentPosition = AccountStockData.getDummy();

        assertThrows(IllegalArgumentException.class, () ->
                AccountStockHelper.updateStockBalance(operationEnum, currentPosition, null));
    }

    @Test
    void updateStockBalanceShouldThrowIllegalArgumentExceptionWhenOperationIsNull() {
        OperationEnum operationEnum = OperationEnum.SELL;
        var currentPosition = AccountStockData.getDummy();

        assertThrows(IllegalArgumentException.class, () ->
                AccountStockHelper.updateStockBalance(null, currentPosition, 1L));
    }

    @Test
    void updateStockBalanceShouldThrowNullArgumentExceptionWhenPositionIsNull() {
        OperationEnum operationEnum = OperationEnum.SELL;
        assertThrows(IllegalArgumentException.class, () ->
                AccountStockHelper.updateStockBalance(operationEnum, null, 1L));
    }

    @Test
    void updateStockBalanceShouldThrowIllegalArgumentExceptionWhenStockQuantityIsNegative() {
        OperationEnum operationEnum = OperationEnum.SELL;
        var currentPosition = AccountStockData.getDummy();

        assertThrows(IllegalArgumentException.class, () ->
                AccountStockHelper.updateStockBalance(operationEnum, currentPosition, -1L));
    }

    @Test
    void updateStockBalanceShouldThrowIllegalArgumentExceptionWhenAccountHasNegativeStockNumber() {
        OperationEnum operationEnum = OperationEnum.SELL;
        var currentPosition = AccountStockData.getDummy();
        currentPosition.setQuantity(-1L);

        assertThrows(IllegalArgumentException.class, () ->
                AccountStockHelper.updateStockBalance(operationEnum, currentPosition, 1L));
    }
}