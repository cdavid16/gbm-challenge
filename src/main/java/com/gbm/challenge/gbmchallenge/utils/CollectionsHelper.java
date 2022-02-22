package com.gbm.challenge.gbmchallenge.utils;

import lombok.experimental.UtilityClass;

import java.util.Collection;

@UtilityClass
public class CollectionsHelper {

    public static boolean isEmptyOrNull(final Collection collection){
        return collection==null || collection.isEmpty();
    }
}
