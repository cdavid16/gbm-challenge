package com.gbm.challenge.gbmchallenge.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CurrentBalance {

    @JsonProperty("cash") private Double cash;
    @JsonProperty("issuers") private List<IssuerResponse> issuers;

}
