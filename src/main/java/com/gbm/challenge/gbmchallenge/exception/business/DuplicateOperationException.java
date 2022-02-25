package com.gbm.challenge.gbmchallenge.exception.business;

import static com.gbm.challenge.gbmchallenge.enums.ExceptionEnum.DUPLICATE_OPERATION_EXCEPTION_MESSAGE;

public class DuplicateOperationException extends BusinessException {

    public DuplicateOperationException() {
        super(DUPLICATE_OPERATION_EXCEPTION_MESSAGE);
    }

}
