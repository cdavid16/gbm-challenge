package com.gbm.challenge.gbmchallenge.mockdata;

import com.gbm.challenge.gbmchallenge.model.request.OperationDto;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class SendOrderDtoValidData {

    private static final Long VALID_TIMESTAMP = 1645813301000L;

    private static final Long VALID_TIMESTAMP_4_MINS_DIFF_AFTER = 1645813541000L;

    private static final Long VALID_TIMESTAMP_4_MINS_DIFF_BEFORE = 1645813061000L;

    private static final Long VALID_TIMESTAMP_6_MINS_DIFF_AFTER = 1645813661000L;

    private static final Long VALID_TIMESTAMP_6_MINS_DIFF_BEFORE = 1645812941000L;

    public static SendOrderDto getSingleOrderBuy() {
        return new SendOrderDto(VALID_TIMESTAMP,
                "BUY",
                "AAPL",
                10L,
                100.0,
                null);
    }

    public static SendOrderDto getSingleOrderBeforeFourMinutesBuy() {
        return new SendOrderDto(VALID_TIMESTAMP_4_MINS_DIFF_BEFORE,
                "BUY",
                "AMZN",
                10L,
                1010.50,
                null);
    }

    public static SendOrderDto getSingleOrderBeforeFourMinutesSell() {
        return new SendOrderDto(VALID_TIMESTAMP_4_MINS_DIFF_BEFORE,
                "SELL",
                "AMZN",
                10L,
                1010.50,
                null);
    }

    public static SendOrderDto getSingleOrderAfterFourMinutesBuy() {
        return new SendOrderDto(VALID_TIMESTAMP_4_MINS_DIFF_AFTER,
                "BUY",
                "AMZN",
                10L,
                1010.50,
                null);
    }

    public static SendOrderDto getSingleOrderAfterFourMinutesBuyDifferent() {
        return new SendOrderDto(VALID_TIMESTAMP_4_MINS_DIFF_AFTER,
                "BUY",
                "AMZN",
                11L,
                1010.50,
                null);
    }

    public static SendOrderDto getSingleOrderBuyBeforeSixMins() {
        return new SendOrderDto(VALID_TIMESTAMP_6_MINS_DIFF_BEFORE,
                "BUY",
                "AMZN",
                10L,
                1010.50,
                null);
    }

    public static SendOrderDto getSingleOrderBuyAfterSixMins() {
        return new SendOrderDto(VALID_TIMESTAMP_6_MINS_DIFF_AFTER,
                "BUY",
                "AMZN",
                10L,
                1010.50,
                null);
    }

    public static SendOrderDto getSingleOrderSell() {
        return new SendOrderDto(VALID_TIMESTAMP,
                "SELL",
                "AAPL",
                10L,
                100.0,
                null);
    }

    public static SendOrderDto getMultiOrder() {
        return new SendOrderDto(VALID_TIMESTAMP,
                null,
                null,
                null,
                null,
                List.of(
                        new OperationDto("BUY",
                                "AAPL",
                                10L,
                                100.0),
                        new OperationDto("SELL",
                                "AMZN",
                                100L,
                                1.0)
                ));
    }

    public static SendOrderDto getSingleOrderOverpriced() {
        return new SendOrderDto(VALID_TIMESTAMP,
                "BUY",
                "AAPL",
                10L,
                1000000000.0,
                null);
    }

    public static SendOrderDto getSingleOrderOverQuantity() {
        return new SendOrderDto(VALID_TIMESTAMP,
                "SELL",
                "AAPL",
                10000000000L,
                10.0,
                null);
    }
}
