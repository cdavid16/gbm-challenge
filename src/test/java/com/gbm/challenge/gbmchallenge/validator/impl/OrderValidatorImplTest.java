package com.gbm.challenge.gbmchallenge.validator.impl;

import com.gbm.challenge.gbmchallenge.BaseTestWithData;
import com.gbm.challenge.gbmchallenge.exception.business.ClosedMarketException;
import com.gbm.challenge.gbmchallenge.exception.business.DuplicateOperationException;
import com.gbm.challenge.gbmchallenge.exception.business.InsufficientBalanceException;
import com.gbm.challenge.gbmchallenge.exception.business.InsufficientStocksException;
import com.gbm.challenge.gbmchallenge.mockdata.SendOrderDtoValidData;
import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import com.gbm.challenge.gbmchallenge.validator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderValidatorImplTest extends BaseTestWithData {

    private OrderValidator validator;
    private final MarketClosedValidator marketClosedValidator = mock(MarketClosedValidator.class);
    private final DuplicateOperationValidator duplicateOperationValidator = mock(DuplicateOperationValidator.class);
    private final StockBalanceValidator stockBalanceValidator = mock(StockBalanceValidator.class);
    private final OrderBalanceValidator orderBalanceValidator = mock(OrderBalanceValidator.class);

    @BeforeEach
    void setup() {
        validator = new OrderValidatorImpl(marketClosedValidator, duplicateOperationValidator, stockBalanceValidator,
                orderBalanceValidator);
    }

    @Test
    void validateShouldThrowIllegalArgumentExceptionWhenOrdersIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.validate(null, mock(AccountEntity.class)));
    }

    @Test
    void validateShouldThrowIllegalArgumentExceptionWhenAccountEntityIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.validate(mock(SendOrderDto.class), null));
    }

    @Test
    void validateShouldThrowMarketClosedExceptionWhenMarketIsClosed() {
        SendOrderDto order = SendOrderDtoValidData.getSingleOrderBuy();
        AccountEntity account = accountData.getDummyAccount();

        when(marketClosedValidator.validateMarketOpen(any())).thenThrow(ClosedMarketException.class);

        assertThrows(ClosedMarketException.class,
                () -> validator.validate(order, account));
    }

    @Test
    void validateShouldThrowDuplicateOperationExceptionWhenOperationIsDuplicate() {
        SendOrderDto order = SendOrderDtoValidData.getSingleOrderBuy();
        AccountEntity account = accountData.getDummyAccount();

        when(duplicateOperationValidator.validateDuplicateOperation(any(), any()))
                .thenThrow(DuplicateOperationException.class);

        assertThrows(DuplicateOperationException.class,
                () -> validator.validate(order, account));
    }

    @Test
    void validateShouldThrowInsufficientStocksExceptionWhenThereAreNoEnoughStocks() {
        SendOrderDto order = SendOrderDtoValidData.getSingleOrderBuy();
        AccountEntity account = accountData.getDummyAccount();

        when(stockBalanceValidator.validateStockBalance(any(), any()))
                .thenThrow(InsufficientStocksException.class);

        assertThrows(InsufficientStocksException.class,
                () -> validator.validate(order, account));
    }

    @Test
    void validateShouldThrowInsufficientBalanceExceptionWhenAccountHasNoEnoughCredit() {
        SendOrderDto order = SendOrderDtoValidData.getSingleOrderBuy();
        AccountEntity account = accountData.getDummyAccount();

        when(orderBalanceValidator.validateBalance(any(), any()))
                .thenThrow(InsufficientBalanceException.class);

        assertThrows(InsufficientBalanceException.class,
                () -> validator.validate(order, account));
    }
}