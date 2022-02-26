package com.gbm.challenge.gbmchallenge.model.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
public class OperationDto {

    @NonNull private String operation;
    @NonNull private String issuer;
    @NonNull private Long totalShares;
    @NonNull private Double sharePrice;

}
