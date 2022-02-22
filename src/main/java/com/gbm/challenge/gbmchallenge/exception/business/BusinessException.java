package com.gbm.challenge.gbmchallenge.exception.business;

public abstract class BusinessException extends RuntimeException {

    public BusinessException(String msg) {
        super(msg);
    }

}
