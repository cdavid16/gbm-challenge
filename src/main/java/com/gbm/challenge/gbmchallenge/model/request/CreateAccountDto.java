package com.gbm.challenge.gbmchallenge.model.request;

import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;

@Getter
public class CreateAccountDto {

    @NonNull private BigDecimal cash;

}
