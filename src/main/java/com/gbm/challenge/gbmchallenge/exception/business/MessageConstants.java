package com.gbm.challenge.gbmchallenge.exception.business;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageConstants {

    protected static final String INSUFFICIENT_STOCK_EXCEPTION_MESSAGE = "You do not have enough stocks to perform this operation.";

    protected static final String INVALID_OPERATION_EXCEPTION_MESSAGE = "The operation you are trying to execute is not valid, please check.";

    protected static final String INSUFFICIENT_BALANCE_EXCEPTION_MESSAGE = "You have insufficient balance to perform this operation.";

    protected static final String DUPLICATE_OPERATION_EXCEPTION_MESSAGE = "You have already done this operation, hence this is a duplicate.";

    protected static final String CLOSED_MARKET_EXCEPTION_MESSAGE = "You cannot submit this operation because market has closed.";

    protected static final String INVALID_ACCOUNT_EXCEPTION = "The account you are trying to use does not exist.";

}
