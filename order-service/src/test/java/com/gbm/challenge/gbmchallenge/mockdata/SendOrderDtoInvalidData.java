package com.gbm.challenge.gbmchallenge.mockdata;

import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SendOrderDtoInvalidData {

    private static final Long VALID_TIMESTAMP = 1645813301000L;

    private static final Long INVALID_TIMESTAMP = 1645852901000L;

    public static SendOrderDto getInvalidOperation() {
        return new SendOrderDto(VALID_TIMESTAMP,
                "BUY_THIS",
                "AAPL",
                10L,
                100.0,
                null);
    }

    public static SendOrderDto getInvalidIssuer() {
        return new SendOrderDto(VALID_TIMESTAMP,
                "BUY",
                "APPLE",
                10L,
                100.0,
                null);
    }

    public static SendOrderDto getInvalidShares() {
        return new SendOrderDto(VALID_TIMESTAMP,
                "BUY",
                "AAPL",
                -10L,
                100.0,
                null);
    }

    public static SendOrderDto getInvalidSharePrice() {
        return new SendOrderDto(VALID_TIMESTAMP,
                "BUY",
                "AAPL",
                10L,
                -100.0,
                null);
    }

    public static SendOrderDto getInvalidTimestamp() {
        return new SendOrderDto(INVALID_TIMESTAMP,
                "BUY",
                "AAPL",
                10L,
                -100.0,
                null);
    }

    public static SendOrderDto getOrderWithNullTimestamp() {
        return new SendOrderDto(null,
                "BUY",
                "AAPL",
                10L,
                -100.0,
                null);
    }

}
