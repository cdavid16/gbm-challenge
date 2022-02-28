package com.gbm.challenge.gbmchallenge.mapper;

import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.model.response.CreateAccountResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.Collections;

public class AccountEntityResponseMapper extends ModelMapper {

    public AccountEntityResponseMapper() {
        TypeMap<AccountEntity, CreateAccountResponseDto> propMapper = createTypeMap(AccountEntity.class,
                                                                            CreateAccountResponseDto.class);
        propMapper.addMappings(mapper -> mapper.map(AccountEntity::getAccountId, CreateAccountResponseDto::setAccountId))
                    .addMappings(mapper -> mapper.map(AccountEntity::getBalance, CreateAccountResponseDto::setBalance))
                    .addMappings(mapper -> mapper.map(src -> Collections.EMPTY_LIST, CreateAccountResponseDto::setIssuers));
    }
}
