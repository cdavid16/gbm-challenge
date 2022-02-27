package com.gbm.challenge.gbmchallenge.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class CollectionsHelperTest {

    @Test
    public void shouldBeTrueWhenCollectionIsNull() {
        List<String> collection = null;
        var actual = CollectionsHelper.isEmptyOrNull(collection);
        Assertions.assertTrue(actual);
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