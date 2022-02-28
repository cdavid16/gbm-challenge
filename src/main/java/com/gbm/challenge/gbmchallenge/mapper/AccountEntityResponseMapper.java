package com.gbm.challenge.gbmchallenge.mapper;

import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.model.response.CreateAccountDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.Collections;

public class AccountEntityResponseMapper extends ModelMapper {

    public AccountEntityResponseMapper() {
        TypeMap<AccountEntity, CreateAccountDto> propMapper = createTypeMap(AccountEntity.class,
                                                                            CreateAccountDto.class);
        propMapper.addMappings(mapper -> mapper.map(AccountEntity::getAccountId, CreateAccountDto::setAccountId))
                    .addMappings(mapper -> mapper.map(AccountEntity::getBalance, CreateAccountDto::setBalance))
                    .addMappings(mapper -> mapper.map(src -> Collections.EMPTY_LIST, CreateAccountDto::setIssuers));
    }
}
