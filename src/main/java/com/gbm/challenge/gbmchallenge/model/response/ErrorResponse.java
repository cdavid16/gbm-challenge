package com.gbm.challenge.gbmchallenge.model.response;

import com.gbm.challenge.gbmchallenge.model.request.OperationDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class ErrorResponse {

    private final Long cash;
    private final OperationDto operation;
    private final List<OperationDto> operations;

}
