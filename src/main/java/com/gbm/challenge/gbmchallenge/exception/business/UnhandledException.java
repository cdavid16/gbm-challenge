package com.gbm.challenge.gbmchallenge.exception.business;

import static com.gbm.challenge.gbmchallenge.enums.ExceptionEnum.UNHANDLED_EXCEPTION;

public class UnhandledException extends BusinessException {
    public UnhandledException() {
        super(UNHANDLED_EXCEPTION);
    }
}
