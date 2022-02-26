package com.gbm.challenge.gbmchallenge.validator.impl;

import com.gbm.challenge.gbmchallenge.enums.OperationEnum;
import com.gbm.challenge.gbmchallenge.exception.business.InsufficientBalanceException;
import com.gbm.challenge.gbmchallenge.model.request.OperationDto;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import com.gbm.challenge.gbmchallenge.utils.OperationEnumHelper;
import com.gbm.challenge.gbmchallenge.validator.OrderBalanceValidator;
import org.apache.commons.lang3.Validate;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static com.gbm.challenge.gbmchallenge.utils.OperationHelper.computeAmountApplied;

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
            OperationEnum transaction = OperationEnumHelper.parseStringToEnum(operation.getOperation());
            balance += computeAmountApplied(transaction, operation.getTotalShares(),
                    operation.getSharePrice());
        }
        if(currentBalance + balance < 0) {
            throw new InsufficientBalanceException();
        }
    }
}
