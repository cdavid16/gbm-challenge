package com.gbm.challenge.gbmchallenge.factory;

import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.model.response.CreateAccountResponse;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

@UtilityClass
public class CreateAccountResponseFactory {

    private static final ModelMapper MAPPER;

    static {
        MAPPER = new ModelMapper();
    }

    public static CreateAccountResponse CreateCreateAccountResponse(AccountEntity accountEntity) {
        return MAPPER.map(accountEntity, CreateAccountResponse.class);
    }

}
