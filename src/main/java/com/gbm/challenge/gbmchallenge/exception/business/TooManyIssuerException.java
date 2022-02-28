package com.gbm.challenge.gbmchallenge.exception.business;

import static com.gbm.challenge.gbmchallenge.enums.ExceptionEnum.TOO_MANY_ISSUERS_EXCEPTION;

public class TooManyIssuerException extends BusinessException {

    public TooManyIssuerException() {
        super(TOO_MANY_ISSUERS_EXCEPTION);
    }
}
