package com.gbm.challenge.gbmchallenge.model.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class IssuerResponse {

    @JsonAlias("issuer_name") private final String issuerName;
    @JsonAlias("total_shares") private final Long totalShares;
    @JsonAlias("share_prices") private final Double sharePrice;

}
