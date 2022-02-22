package com.gbm.challenge.gbmchallenge.exception.business;

import org.springframework.web.bind.annotation.ResponseStatus;

import static com.gbm.challenge.gbmchallenge.exception.business.MessageConstants.INSUFFICIENT_STOCK_EXCEPTION_MESSAGE;

@ResponseStatus(reason = INSUFFICIENT_STOCK_EXCEPTION_MESSAGE)
public class InsufficientStocksException extends BusinessException {

    public InsufficientStocksException() {
        super(INSUFFICIENT_STOCK_EXCEPTION_MESSAGE);
    }

}
