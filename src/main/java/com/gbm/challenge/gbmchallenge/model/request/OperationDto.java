package com.gbm.challenge.gbmchallenge.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OperationDto {

    @JsonProperty("operation") private String operation;
    @JsonProperty("issuer_name") private String issuer;
    @JsonProperty("total_shares") private Long totalShares;
    @JsonProperty("share_price") private Double sharePrice;

}
