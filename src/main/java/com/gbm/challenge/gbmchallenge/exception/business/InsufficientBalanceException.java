package com.gbm.challenge.gbmchallenge.exception.business;

import org.springframework.web.bind.annotation.ResponseStatus;

import static com.gbm.challenge.gbmchallenge.exception.business.MessageConstants.INSUFFICIENT_BALANCE_EXCEPTION_MESSAGE;

@ResponseStatus(reason = INSUFFICIENT_BALANCE_EXCEPTION_MESSAGE)
public class InsufficientBalanceException extends BusinessException {

    public InsufficientBalanceException() {
        super(INSUFFICIENT_BALANCE_EXCEPTION_MESSAGE);
    }

}
