package com.gbm.challenge.gbmchallenge.exception.business;

import static com.gbm.challenge.gbmchallenge.enums.ExceptionEnum.INSUFFICIENT_BALANCE_EXCEPTION_MESSAGE;

public class InsufficientBalanceException extends BusinessException {

    public InsufficientBalanceException() {
        super(INSUFFICIENT_BALANCE_EXCEPTION_MESSAGE);
    }

}
