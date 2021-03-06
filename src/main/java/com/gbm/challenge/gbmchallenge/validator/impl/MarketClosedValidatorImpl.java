package com.gbm.challenge.gbmchallenge.validator.impl;

import com.gbm.challenge.gbmchallenge.exception.business.ClosedMarketException;
import com.gbm.challenge.gbmchallenge.exception.business.FutureDateException;
import com.gbm.challenge.gbmchallenge.utils.NumberHelper;
import com.gbm.challenge.gbmchallenge.validator.MarketClosedValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;
import java.util.TimeZone;

@Slf4j
@Component
public class MarketClosedValidatorImpl implements MarketClosedValidator {

    private static final Integer OPEN_HOUR = 6;
    private static final Integer CLOSE_HOUR = 15;
    private static final Set<DayOfWeek> WORKING_DAYS = Set.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY, DayOfWeek.FRIDAY);

    @Override
    public boolean validateMarketOpen(@Validated final Long timestamp) {
        NumberHelper.isPositive(timestamp);
        log.info("Current Timezone: {}", TimeZone.getDefault());
        LocalDateTime localDateTime = new Timestamp(timestamp).toLocalDateTime();
        log.info("Timestamp of the request: {}", localDateTime);
        isFutureDate(localDateTime);
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

    private void isFutureDate(final LocalDateTime localDateTime) {
        LocalDateTime now = LocalDateTime.now();
        if (localDateTime.isAfter(now)) {
            throw new FutureDateException();
        }
    }
}
