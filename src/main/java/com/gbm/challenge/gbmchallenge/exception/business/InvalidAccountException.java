package com.gbm.challenge.gbmchallenge.exception.business;

import static com.gbm.challenge.gbmchallenge.enums.ExceptionEnum.INVALID_ACCOUNT_EXCEPTION;

public class InvalidAccountException extends BusinessException {

    public InvalidAccountException() {
        super(INVALID_ACCOUNT_EXCEPTION);
    }
}
