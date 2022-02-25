package com.gbm.challenge.gbmchallenge.validator;

import com.gbm.challenge.gbmchallenge.model.entities.TransactionEntity;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;

import java.util.Set;

@FunctionalInterface
public interface DuplicateOperationValidator {

    void validateDuplicateOperation(SendOrderDto orders, Set<TransactionEntity> transactions);

}
