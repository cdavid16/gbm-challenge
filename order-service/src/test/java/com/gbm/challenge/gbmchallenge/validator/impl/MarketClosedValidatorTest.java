package com.gbm.challenge.gbmchallenge.validator.impl;

import com.gbm.challenge.gbmchallenge.exception.business.ClosedMarketException;
import com.gbm.challenge.gbmchallenge.validator.MarketClosedValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MarketClosedValidatorTest {

    private MarketClosedValidator validator;

    @BeforeEach
    void setup() {
        validator = new MarketClosedValidatorImpl();
    }

    @Test
    void validateMarketOpenShouldThrowIllegalArgumentExceptionWhenTimestampIsNull() {
        Long timestamp = null;

        assertThrows(IllegalArgumentException.class, ()-> validator.validateMarketOpen(timestamp));
    }

    @Test
    void validateMarketOpenShouldThrowIllegalArgumentExceptionWhenTimestampIsNegative() {
        Long timestamp = -1L;

        assertThrows(IllegalArgumentException.class, ()-> validator.validateMarketOpen(timestamp));
    }

    @Test
    void validateMarketOpenShouldThrowClosedMarketExceptionWhenTimestampIsWeekend() {
        Long timestamp = parseStringToDate("30-01-2022", new SimpleDateFormat("dd-MM-yyyy"));

        assertThrows(ClosedMarketException.class, ()-> validator.validateMarketOpen(timestamp));
    }

    @Test
    void validateMarketOpenShouldThrowClosedMarketExceptionWhenTimestampIsOutOfHours() {
        Long timestamp = parseStringToDate("07-02-2022 20:00:00",
                new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"));

        assertThrows(ClosedMarketException.class, ()-> validator.validateMarketOpen(timestamp));
    }

    @Test
    void validateMarketOpenShouldThrowClosedMarketExceptionWhenHourIsInRangeButIsWeekend() {
        Long timestamp = parseStringToDate("30-01-2022 12:00:00",
                new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"));

        assertThrows(ClosedMarketException.class, ()-> validator.validateMarketOpen(timestamp));
    }

    @Test
    void validateMarketOpenShouldThrowClosedMarketExceptionWhenMarketHasNotOpenYet() {
        Long timestamp = parseStringToDate("07-02-2022 05:59:59",
                new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"));

        assertThrows(ClosedMarketException.class, ()-> validator.validateMarketOpen(timestamp));
    }

    @Test
    void validateMarketOpenShouldThrowClosedMarketExceptionWhenMarketJustClose() {
        Long timestamp = parseStringToDate("07-02-2022 15:00:00",
                new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"));

        assertThrows(ClosedMarketException.class, ()-> validator.validateMarketOpen(timestamp));
    }

    @Test
    void validateMarketOpenShouldReturnTrueWhenMarketJustOpens() {
        Long timestamp = parseStringToDate("07-02-2022 06:00:00",
                new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"));

        assertTrue(validator.validateMarketOpen(timestamp));
    }

    @Test
    void validateMarketOpenShouldReturnTrueWhenMarketIsAboutToClose() {
        Long timestamp = parseStringToDate("07-02-2022 14:59:59",
                new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"));

        assertTrue(validator.validateMarketOpen(timestamp));
    }

    private Long parseStringToDate(String date, SimpleDateFormat formatter) {
        try{
            Date formatDate = formatter.parse(date);
            return new Timestamp(formatDate.getTime()).getTime();
        } catch(Exception e) {
            return null;
        }
    }
}