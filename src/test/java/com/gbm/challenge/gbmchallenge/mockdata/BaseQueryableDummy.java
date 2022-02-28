package com.gbm.challenge.gbmchallenge.mockdata;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class BaseQueryableDummy <T> {

    public abstract Set<T> getDetails();

    protected Set<T> getTransactionDetails(Predicate<T> query) {
        return getDetails()
                .stream()
                .filter(query)
                .collect(Collectors.toSet());
    }

}
