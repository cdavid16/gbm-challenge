package com.gbm.challenge.gbmchallenge.exception.business;

import org.springframework.web.bind.annotation.ResponseStatus;

import static com.gbm.challenge.gbmchallenge.exception.business.MessageConstants.DUPLICATE_OPERATION_EXCEPTION_MESSAGE;

@ResponseStatus(reason = DUPLICATE_OPERATION_EXCEPTION_MESSAGE)
public class DuplicateOperationException extends BusinessException {

    public DuplicateOperationException() {
        super(DUPLICATE_OPERATION_EXCEPTION_MESSAGE);
    }

}
