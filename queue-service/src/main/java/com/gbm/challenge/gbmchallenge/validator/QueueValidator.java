package com.gbm.challenge.gbmchallenge.validator;

import com.gbm.challenge.gbmchallenge.model.RequestDto;

@FunctionalInterface
public interface QueueValidator {

    boolean validate(String accountId, RequestDto requestDto);

}
