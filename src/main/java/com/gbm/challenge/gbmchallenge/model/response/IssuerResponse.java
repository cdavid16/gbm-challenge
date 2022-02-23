package com.gbm.challenge.gbmchallenge.model.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@RequiredArgsConstructor
public class IssuerResponse {

    @JsonAlias("issuer_name") private final String issuerName;
    @JsonAlias("total_shares") private final BigInteger totalShares;
    @JsonAlias("share_prices") private final BigDecimal sharePrice;

}
