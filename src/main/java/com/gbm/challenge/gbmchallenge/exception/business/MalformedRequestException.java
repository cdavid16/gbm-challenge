package com.gbm.challenge.gbmchallenge.exception.business;

import static com.gbm.challenge.gbmchallenge.enums.ExceptionEnum.MALFORMED_REQUEST_EXCEPTION;

public class MalformedRequestException extends BusinessException {

    public MalformedRequestException() {
        super(MALFORMED_REQUEST_EXCEPTION);
    }

}
