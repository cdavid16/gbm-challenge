package com.gbm.challenge.gbmchallenge.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class CollectionsHelperTest {

    @SuppressWarnings("ConstantConditions")
    @Test
    public void shouldBeTrueWhenCollectionIsNull() {
        Assertions.assertTrue(CollectionsHelper.isEmptyOrNull(null));
    }

    @Test
    public void shouldBeTrueWhenCollectionIsEmpty() {
        var collection = List.of();
        var actual = CollectionsHelper.isEmptyOrNull(collection);
        Assertions.assertTrue(actual);
    }

    @Test
    public void shouldBeFalseWhenCollectionIsNotEmpty() {
        var collection = List.of("Test");
        var actual = CollectionsHelper.isEmptyOrNull(collection);
        Assertions.assertFalse(actual);
    }

}