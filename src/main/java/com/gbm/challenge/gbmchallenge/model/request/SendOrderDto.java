package com.gbm.challenge.gbmchallenge.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gbm.challenge.gbmchallenge.utils.CollectionsHelper;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
public class SendOrderDto {

    private Long timestamp;
    private OperationDto operation;
    private List<OperationDto> operations;

    @JsonIgnore
    public boolean isMultiOperations(){
        return CollectionsHelper.isEmptyOrNull(operations);
    }

    public List<OperationDto> getOperations() {
        if (isMultiOperations()) {
            return this.getOperations();
        }
        return List.of(operation);
    }

}
