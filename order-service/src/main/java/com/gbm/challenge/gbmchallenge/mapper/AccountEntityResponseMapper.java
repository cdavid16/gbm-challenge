package com.gbm.challenge.gbmchallenge.mapper;

import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.model.response.CreateAccountResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.Collections;

public class AccountEntityResponseMapper extends ModelMapper {

    public AccountEntityResponseMapper() {
        TypeMap<AccountEntity, CreateAccountResponse> propMapper = createTypeMap(AccountEntity.class,
                                                                            CreateAccountResponse.class);
        propMapper.addMappings(mapper -> mapper.map(AccountEntity::getAccountId, CreateAccountResponse::setAccountId))
                    .addMappings(mapper -> mapper.map(AccountEntity::getBalance, CreateAccountResponse::setBalance))
                    .addMappings(mapper -> mapper.map(src -> Collections.EMPTY_LIST, CreateAccountResponse::setIssuers));
    }
}
