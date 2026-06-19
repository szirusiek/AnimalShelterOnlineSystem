package org.example.tests;

import org.example.repository.DataRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class DataRepositoryTest {

    @Test
    void returnAnimalsIfPrefixTest() {

        DataRepository repo = new DataRepository();

        List<Object> result = repo.getObjects("animal");

        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void returnEmptyListForInvalidPrefixTest() {

        DataRepository repo = new DataRepository();

        List<Object> result = repo.getObjects("err");

        Assertions.assertTrue(result.isEmpty());
    }
}