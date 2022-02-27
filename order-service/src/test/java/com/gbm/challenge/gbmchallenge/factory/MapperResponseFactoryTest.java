package com.gbm.challenge.gbmchallenge.factory;

import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.model.entities.TransactionDetailEntity;
import com.gbm.challenge.gbmchallenge.model.response.CreateAccountResponse;
import com.gbm.challenge.gbmchallenge.model.response.SendOrderResponse;
import org.junit.jupiter.api.Test;

import static com.gbm.challenge.gbmchallenge.factory.MapperResponseFactory.createNegativeResponse;
import static com.gbm.challenge.gbmchallenge.factory.MapperResponseFactory.createPositiveResponse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapperResponseFactoryTest {

    @Test
    void createPositiveResponseShouldCreatePositiveResponseWhenMappingExists() {
        AccountEntity source = new AccountEntity();
        Object dest = createPositiveResponse(source, CreateAccountResponse.class);

        assertTrue(dest instanceof CreateAccountResponse);
    }

    @Test
    void createPositiveResponseShouldThrowIllegalArgumentExceptionWhenThereIsNoMapping() {
        TransactionDetailEntity source = new TransactionDetailEntity();
        assertThrows(IllegalArgumentException.class, () -> createPositiveResponse(source,
                CreateAccountResponse.class));
    }

    @Test
    void createPositiveResponseShouldThrowIllegalArgumentExceptionWhenSourceIsNull() {
        assertThrows(IllegalArgumentException.class, () -> createPositiveResponse(null,
                CreateAccountResponse.class));
    }

    @Test
    void createNegativeResponseShouldThrowIllegalArgumentExceptionWhenExceptionCodeIsNull() {
        assertThrows(IllegalArgumentException.class, () -> createNegativeResponse(null, "200"));
    }

    @Test
    void createNegativeResponseShouldThrowIllegalArgumentExceptionWhenCashIsNull() {
        assertThrows(IllegalArgumentException.class, () -> createNegativeResponse("DUMMY", null));
    }

    @Test
    void createNegativeResponseShouldThrowIllegalArgumentExceptionWhenCashIsNotANumber() {
        assertThrows(IllegalArgumentException.class, () -> createNegativeResponse("DUMMY", "Not a number"));
    }

    @Test
    void createNegativeResponseShouldCreateNegativeResponseWhenInputIsValid() {
        SendOrderResponse response = createNegativeResponse("DUMMY", "123");
        assertTrue(response.getExceptions().size() > 0);
    }
}