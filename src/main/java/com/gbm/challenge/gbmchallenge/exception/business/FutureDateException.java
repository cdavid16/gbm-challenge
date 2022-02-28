package com.gbm.challenge.gbmchallenge.exception.business;

import static com.gbm.challenge.gbmchallenge.enums.ExceptionEnum.FUTURE_DATE_EXCEPTION;

public class FutureDateException extends BusinessException {

    public FutureDateException() {
        super(FUTURE_DATE_EXCEPTION);
    }

}
