package com.gbm.challenge.gbmchallenge.model.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class CreateAccountResponse {

    @JsonAlias("id") private final String id;
    @JsonAlias("cash") private final BigDecimal cash;
    @JsonAlias("issuers") private final List<IssuerResponse> issuers;

}
