package com.gbm.challenge.gbmchallenge.model.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
public class CreateAccountDto {

    @NonNull private Double cash;

}
