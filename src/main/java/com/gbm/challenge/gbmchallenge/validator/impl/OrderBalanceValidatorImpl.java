package com.gbm.challenge.gbmchallenge.validator.impl;

import com.gbm.challenge.gbmchallenge.enums.TransactionEnum;
import com.gbm.challenge.gbmchallenge.exception.business.InsufficientBalanceException;
import com.gbm.challenge.gbmchallenge.model.request.OperationDto;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import com.gbm.challenge.gbmchallenge.utils.TransactionHelper;
import com.gbm.challenge.gbmchallenge.validator.OrderBalanceValidator;
import org.apache.commons.lang3.Validate;
import org.springframework.validation.annotation.Validated;

import java.util.List;


public class OrderBalanceValidatorImpl implements OrderBalanceValidator {

    private OrderBalanceValidatorImpl() {}

    private static final OrderBalanceValidator INSTANCE = new OrderBalanceValidatorImpl();

    protected static OrderBalanceValidator getInstance() { return INSTANCE; }

    @Override
    public void validateBalance(@Validated final SendOrderDto orders, @Validated final Double currentBalance) {
        Validate.notNull(orders, "The orders to be validated cannot be null");
        Validate.notNull(currentBalance, "Current balance cannot be null");
        final List<OperationDto> operations = orders.getOperations();
        double balance = 0.0;
        for(OperationDto operation : operations) {
            TransactionEnum transaction = TransactionHelper.parseStringToEnum(operation.getOperation());
            Double amount = computeAmountApplied(transaction, operation.getTotalShares(), operation.getSharePrice());
            balance += amount;
        }
        if(currentBalance + balance < 0) {
            throw new InsufficientBalanceException();
        }
    }

    private Double computeAmountApplied(final TransactionEnum transaction, final Long totalShares,
                                        final Double sharePrice) {
        return totalShares * sharePrice * transaction.getBalanceFactor() * -1;
    }
}
