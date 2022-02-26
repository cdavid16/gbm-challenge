package com.gbm.challenge.gbmchallenge.exception.business;

import com.gbm.challenge.gbmchallenge.enums.ExceptionEnum;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public abstract class BusinessException extends RuntimeException {

    private String exceptionCode;

    public BusinessException(ExceptionEnum exception) {
        super(exception.getReason());
        this.exceptionCode = exception.getCode();
    }

}
