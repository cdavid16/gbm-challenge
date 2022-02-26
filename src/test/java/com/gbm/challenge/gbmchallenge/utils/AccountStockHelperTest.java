package com.gbm.challenge.gbmchallenge.utils;

import com.gbm.challenge.gbmchallenge.enums.OperationEnum;
import com.gbm.challenge.gbmchallenge.exception.business.InsufficientStocksException;
import com.gbm.challenge.gbmchallenge.mockdata.AccountStockData;
import com.gbm.challenge.gbmchallenge.model.entities.AccountStockEntity;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AccountStockHelperTest {

    @Test
    void buildBalanceMapShouldReturnAllTransactionsAsMap() {
        Set<AccountStockEntity> stocks = AccountStockData.getInstance().getTransactionDetails();

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