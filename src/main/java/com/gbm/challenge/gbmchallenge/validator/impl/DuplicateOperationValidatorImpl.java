package com.gbm.challenge.gbmchallenge.validator.impl;

import com.gbm.challenge.gbmchallenge.exception.business.DuplicateOperationException;
import com.gbm.challenge.gbmchallenge.model.entities.TransactionEntity;
import com.gbm.challenge.gbmchallenge.model.request.OperationDto;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import com.gbm.challenge.gbmchallenge.validator.DuplicateOperationValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.util.Validate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DuplicateOperationValidatorImpl implements DuplicateOperationValidator {

    private static final Long MILLIS_5_MINUTES = 300000L;

    @Override
    public boolean validateDuplicateOperation(final SendOrderDto orders,
                                              final Set<TransactionEntity> transactions) {
        Validate.notNull(orders);
        Validate.notNull(orders.getTimestamp());
        Validate.notNull(transactions);
        Set<String> transactionsString = filterTransactionDetails(orders.getTimestamp(), transactions);
        orders.getOperations().forEach(o-> validateTransaction(o, transactionsString));
        return true;
    }

    private void validateTransaction(final OperationDto operationDto, final Set<String> transactionsString) {
        String transactionKey = createKey(operationDto.getOperation(),
                operationDto.getIssuer(), operationDto.getTotalShares());
        if(transactionsString.contains(transactionKey)) {
            throw new DuplicateOperationException();
        }
    }

    private Set<String> filterTransactionDetails(final Long timestamp,
                                                 final Set<TransactionEntity> transactions) {
        Long minValue = timestamp - MILLIS_5_MINUTES;
        Long maxValue = timestamp + MILLIS_5_MINUTES;
        return transactions
                .stream()
                .filter(t -> t.getTimestamp() >= minValue && t.getTimestamp() <= maxValue)
                .flatMap(t-> t.getTransactionDetails().stream())
                .map(d-> createKey(d.getOperation().name(), d.getIssuer().getName(), d.getQuantity()))
                .collect(Collectors.toSet());
    }

    private String createKey(final String operation, final String issuer, final Long quantity) {
        return String.format("%s~%s~%d", operation, issuer, quantity);
    }


}
