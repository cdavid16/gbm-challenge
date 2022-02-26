package com.gbm.challenge.gbmchallenge.validator.impl;

import com.gbm.challenge.gbmchallenge.model.entities.TransactionEntity;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import com.gbm.challenge.gbmchallenge.validator.DuplicateOperationValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class DuplicateOperationValidatorImpl implements DuplicateOperationValidator {

    private static final DuplicateOperationValidator INSTANCE = new DuplicateOperationValidatorImpl();

    private DuplicateOperationValidatorImpl() {}

    public static DuplicateOperationValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean validateDuplicateOperation(SendOrderDto orders, Set<TransactionEntity> transactions) {

        return true;
    }

}
