package com.gbm.challenge.gbmchallenge.mapper;

import com.gbm.challenge.gbmchallenge.model.entities.TransactionEntity;
import com.gbm.challenge.gbmchallenge.model.response.SendOrderResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.Collections;

public class TransactionEntityResponseMapper extends ModelMapper {

    public TransactionEntityResponseMapper() {
        TypeMap<TransactionEntity, SendOrderResponse> propMapper = createTypeMap(TransactionEntity.class,
                SendOrderResponse.class);
        propMapper
                .addMappings(mapper -> mapper.map(src-> src.getAccount().getBalance(),
                        SendOrderResponse::setCash))
                .addMappings(mapper -> mapper.map(TransactionEntity::getTransactionDetails,
                        SendOrderResponse::setIssuerResponse))
                .addMappings(mapper -> mapper.map(src -> Collections.EMPTY_LIST,
                        SendOrderResponse::setExceptions))
        ;
    }

}
