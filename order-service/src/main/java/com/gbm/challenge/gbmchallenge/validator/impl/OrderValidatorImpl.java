package com.gbm.challenge.gbmchallenge.validator.impl;

import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import com.gbm.challenge.gbmchallenge.validator.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.util.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Slf4j
@Component
public class OrderValidatorImpl implements OrderValidator {

    private final MarketClosedValidator marketClosedValidator;
    private final DuplicateOperationValidator duplicateOperationValidator;
    private final StockBalanceValidator stockBalanceValidator;
    private final OrderBalanceValidator orderBalanceValidator;

    @Autowired
    public OrderValidatorImpl(final MarketClosedValidator marketClosedValidator,
                              final DuplicateOperationValidator duplicateOperationValidator,
                              final StockBalanceValidator stockBalanceValidator,
                              final OrderBalanceValidator orderBalanceValidator) {
        this.marketClosedValidator = marketClosedValidator;
        this.duplicateOperationValidator = duplicateOperationValidator;
        this.stockBalanceValidator = stockBalanceValidator;
        this.orderBalanceValidator = orderBalanceValidator;
    }

    @Override
    public void validate(SendOrderDto orders, AccountEntity account) {
        Validate.notNull(orders);
        Validate.notNull(account);
        log.info("Order to validate {} for account {}.", orders, account.getAccountId());
        this.marketClosedValidator.validateMarketOpen(orders.getTimestamp());
        this.duplicateOperationValidator.validateDuplicateOperation(orders, account.getTransactions());
        this.stockBalanceValidator.validateStockBalance(orders, account.getAccountStocks());
        this.orderBalanceValidator.validateBalance(orders, account.getBalance());
        log.info("Order has passed all the validations.");
    }
}
