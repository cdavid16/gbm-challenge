package com.gbm.challenge.gbmchallenge.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OperationEnum {
    BUY(-1),
    SELL(1);

    private final Integer balanceFactor;

}
