package com.gbm.challenge.gbmchallenge.exception.business;

import org.springframework.web.bind.annotation.ResponseStatus;

import static com.gbm.challenge.gbmchallenge.exception.business.MessageConstants.CLOSED_MARKET_EXCEPTION_MESSAGE;

@ResponseStatus(reason = CLOSED_MARKET_EXCEPTION_MESSAGE)
public class ClosedMarketException extends BusinessException {

    public ClosedMarketException() {
        super(CLOSED_MARKET_EXCEPTION_MESSAGE);
    }

}
