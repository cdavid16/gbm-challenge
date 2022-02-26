package com.gbm.challenge.gbmchallenge.validator.impl;

import com.gbm.challenge.gbmchallenge.exception.business.ClosedMarketException;
import com.gbm.challenge.gbmchallenge.utils.NumberHelper;
import com.gbm.challenge.gbmchallenge.validator.MarketClosedValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.util.Validate;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Slf4j
public class MarketClosedValidatorImpl implements MarketClosedValidator {

    private static final Integer OPEN_HOUR = 6;
    private static final Integer CLOSE_HOUR = 15;
    private static final Set<DayOfWeek> WORKING_DAYS = Set.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY, DayOfWeek.FRIDAY);
    private static final MarketClosedValidator INSTANCE = new MarketClosedValidatorImpl();

    private MarketClosedValidatorImpl() {}

    public static MarketClosedValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean validateMarketOpen(@Validated final Long timestamp) {
        NumberHelper.isPositive(timestamp);

        LocalDateTime localDateTime = new Timestamp(timestamp).toLocalDateTime();
        validateDay(localDateTime);
        validateTime(localDateTime.toLocalTime());
        log.info("Market is open.");
        return true;
    }

    private void validateDay(final LocalDateTime localDateTime) {
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        if (!WORKING_DAYS.contains(dayOfWeek)) {
            throw new ClosedMarketException();
        }
    }

    private void validateTime(final LocalTime localTime) {
        int hour = localTime.getHour();
        if (hour<OPEN_HOUR) {
            throw new ClosedMarketException();
        }
        if (hour>=CLOSE_HOUR) {
            throw new ClosedMarketException();
        }
    }
}
