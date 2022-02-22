package com.gbm.challenge.gbmchallenge.exception.business;

import org.springframework.web.bind.annotation.ResponseStatus;

import static com.gbm.challenge.gbmchallenge.exception.business.MessageConstants.INVALID_OPERATION_EXCEPTION_MESSAGE;

@ResponseStatus(reason = INVALID_OPERATION_EXCEPTION_MESSAGE)
public class InvalidOperationException extends BusinessException {

    public InvalidOperationException() {
        super(INVALID_OPERATION_EXCEPTION_MESSAGE);
    }
}
