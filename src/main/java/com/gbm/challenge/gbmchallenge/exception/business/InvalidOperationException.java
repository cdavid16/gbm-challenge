package com.gbm.challenge.gbmchallenge.exception.business;

import static com.gbm.challenge.gbmchallenge.enums.ExceptionEnum.INVALID_OPERATION_EXCEPTION_MESSAGE;

public class InvalidOperationException extends BusinessException {

    public InvalidOperationException() {
        super(INVALID_OPERATION_EXCEPTION_MESSAGE);
    }
}
