package com.gbm.challenge.gbmchallenge.validator.impl;

import com.gbm.challenge.gbmchallenge.exception.business.ClosedMarketException;
import com.gbm.challenge.gbmchallenge.validator.MarketClosedValidator;
import org.apache.commons.lang.util.Validate;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Set;

public class MarketClosedValidatorImpl implements MarketClosedValidator {

    private static final String ZONE = "UTC";
    private static final Integer OPEN_HOUR = 6;
    private static final Integer CLOSE_HOUR = 6;
    private static final Set<DayOfWeek> WORKING_DAYS = Set.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY, DayOfWeek.FRIDAY);
    private static final MarketClosedValidator INSTANCE = new MarketClosedValidatorImpl();

    private MarketClosedValidatorImpl() {}

    public static MarketClosedValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public void validateMarketOpen(@Validated final Long timestamp) {
        Validate.notNull(timestamp, "Given timestamp cannot be null");

        LocalDateTime localDateTime = new Timestamp(timestamp).toInstant().atZone(ZoneId.of(ZONE)).toLocalDateTime();
        validateDay(localDateTime);
        validateTime(localDateTime.toLocalTime());
    }

    private void validateDay(final LocalDateTime localDateTime) {
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        if (WORKING_DAYS.contains(dayOfWeek)) {
            throw new ClosedMarketException();
        }
    }

    private void validateTime(final LocalTime localTime) {
        int hour = localTime.getHour();
        int minutes = localTime.getMinute();
        int seconds = localTime.getSecond();
        int nano = localTime.getNano();
        if (hour<OPEN_HOUR) {
            throw new ClosedMarketException();
        }
        if (hour>CLOSE_HOUR) {
            throw new ClosedMarketException();
        }
        if (hour==CLOSE_HOUR && (minutes>0 || seconds>0 || nano>0)) {
            throw new ClosedMarketException();
        }
    }
}
