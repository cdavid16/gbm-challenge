package com.gbm.challenge.gbmchallenge.exception.business;

import static com.gbm.challenge.gbmchallenge.enums.ExceptionEnum.CLOSED_MARKET_EXCEPTION_MESSAGE;

public class ClosedMarketException extends BusinessException {

    public ClosedMarketException() {
        super(CLOSED_MARKET_EXCEPTION_MESSAGE);
    }

}
