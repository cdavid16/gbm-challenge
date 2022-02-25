package com.gbm.challenge.gbmchallenge.validator.impl;

import com.gbm.challenge.gbmchallenge.model.entities.TransactionEntity;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import com.gbm.challenge.gbmchallenge.validator.DuplicateOperationValidator;

import java.util.Set;

public class DuplicateOperationValidatorImpl implements DuplicateOperationValidator {

    private static final DuplicateOperationValidator INSTANCE = new DuplicateOperationValidatorImpl();

    private DuplicateOperationValidatorImpl() {}

    public static DuplicateOperationValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public void validateDuplicateOperation(SendOrderDto orders, Set<TransactionEntity> transactions) {

    }

}
