package com.gbm.challenge.gbmchallenge.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gbm.challenge.gbmchallenge.utils.CollectionsHelper;
import lombok.*;

import java.util.List;

@Getter
@ToString
public class RequestDto {

    @JsonProperty("account_id") private String accountId;
    @JsonProperty("timestamp") private Long timestamp;
    @JsonProperty("operation") private String operation;
    @JsonProperty("issuer_name") private String issuer;
    @JsonProperty("total_shares") private Long totalShares;
    @JsonProperty("share_price") private Double sharePrice;
    @JsonProperty("operations") private List<OperationDto> operations;

    @JsonIgnore
    public boolean isMultiOperations(){
        return !CollectionsHelper.isEmptyOrNull(operations);
    }

    @JsonIgnore
    public List<OperationDto> getOperations() {
        if (isMultiOperations()) {
            return this.operations;
        }
        return List.of(getOperation());
    }

    @JsonIgnore
    public OperationDto getOperation() {
        return new OperationDto(this.operation, this.issuer, this.totalShares, this.sharePrice);
    }

}