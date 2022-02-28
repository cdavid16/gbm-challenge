package com.gbm.challenge.gbmchallenge.utils;

import lombok.experimental.UtilityClass;

import java.util.Collection;

@UtilityClass
public class CollectionsHelper {

    public static <T> boolean isEmptyOrNull(final Collection<T> collection){
        return collection==null || collection.isEmpty();
    }
}
