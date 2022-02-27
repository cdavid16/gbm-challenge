package com.gbm.challenge.gbmchallenge.exception.business;

import static com.gbm.challenge.gbmchallenge.enums.ExceptionEnum.INSUFFICIENT_STOCK_EXCEPTION_MESSAGE;

public class InsufficientStocksException extends BusinessException {

    public InsufficientStocksException() {
        super(INSUFFICIENT_STOCK_EXCEPTION_MESSAGE);
    }

}
