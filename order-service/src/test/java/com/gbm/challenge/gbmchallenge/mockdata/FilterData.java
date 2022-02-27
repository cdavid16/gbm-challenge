package com.gbm.challenge.gbmchallenge.mockdata;

import com.gbm.challenge.gbmchallenge.model.entities.AccountStockEntity;
import com.gbm.challenge.gbmchallenge.model.entities.TransactionDetailEntity;
import lombok.experimental.UtilityClass;

import java.util.Objects;
import java.util.function.Predicate;

@UtilityClass
public class FilterData {

    static Predicate<TransactionDetailEntity> getTransactionDetailsByIssuerId(final String issuerId) {
        return tde -> tde.getIssuer().getIssuerId().equals(issuerId);
    }

    static Predicate<AccountStockEntity> getAccountStockByIssuerId(final String issuerId) {
        return ase -> ase.getIssuer().getName().equals((issuerId));
    }

    static Predicate<TransactionDetailEntity> getTransactionDetailsByTransactionId(
                                                                            final String transactionId) {
        return tde -> !Objects.isNull(transactionId) && tde.getTransaction().getTransactionId().equals(transactionId);
    }


}
