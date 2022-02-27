package com.gbm.challenge.gbmchallenge.model.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class IssuerResponse {

    @JsonAlias("issuer_name") private String issuerName;
    @JsonAlias("total_shares") private Long totalShares;
    @JsonAlias("share_prices") private Double sharePrice;

}
