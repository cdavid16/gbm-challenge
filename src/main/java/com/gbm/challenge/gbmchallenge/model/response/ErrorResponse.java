package com.gbm.challenge.gbmchallenge.model.response;

import com.gbm.challenge.gbmchallenge.model.request.OperationDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
public class ErrorResponse {

    private final Long cash;
    private final OperationDto operation;
    private final List<OperationDto> operations;

}
