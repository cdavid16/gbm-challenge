package com.gbm.challenge.gbmchallenge.enums;

import lombok.Getter;

@Getter
public enum ExceptionEnum {

    INSUFFICIENT_STOCK_EXCEPTION_MESSAGE("INSUFFICIENT_STOCK_EXCEPTION_MESSAGE",
            "You do not have enough stocks to perform this operation."),
    INVALID_OPERATION_EXCEPTION_MESSAGE("INVALID_OPERATION_EXCEPTION_MESSAGE",
            "The operation you are trying to execute is not valid, please check."),
    INSUFFICIENT_BALANCE_EXCEPTION_MESSAGE("INSUFFICIENT_BALANCE_EXCEPTION_MESSAGE",
            "You have insufficient balance to perform this operation."),
    DUPLICATE_OPERATION_EXCEPTION_MESSAGE("DUPLICATE_OPERATION_EXCEPTION_MESSAGE",
            "You have already done this operation, hence this is a duplicate."),
    CLOSED_MARKET_EXCEPTION_MESSAGE("CLOSED_MARKET_EXCEPTION_MESSAGE",
            "You cannot submit this operation because market has already closed."),
    INVALID_ACCOUNT_EXCEPTION("INVALID_ACCOUNT_EXCEPTION",
            "The account you are trying to use does not exist."),
    INVALID_ISSUER_EXCEPTION("INVALID_ISSUER_EXCEPTION",
            "The issuer you are trying to use does not exist."),
    TOO_MANY_ISSUERS_EXCEPTION("TOO_MANY_ISSUERS_EXCEPTION",
            "The issuer you are trying to use is not unique, this is invalid, please contact IT.")
    ;

    private String code;
    private String reason;

    ExceptionEnum(final String code, final String reason) {
        this.code = code;
        this.reason = reason;
    }

}
