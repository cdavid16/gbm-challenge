package com.gbm.challenge.gbmchallenge.exception.business;


import static com.gbm.challenge.gbmchallenge.enums.ExceptionEnum.INVALID_ISSUER_EXCEPTION;

public class InvalidIssuerException extends BusinessException {

    public InvalidIssuerException() {
        super(INVALID_ISSUER_EXCEPTION);
    }
}
