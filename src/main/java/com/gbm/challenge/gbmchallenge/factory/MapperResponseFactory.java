package com.gbm.challenge.gbmchallenge.factory;

import com.gbm.challenge.gbmchallenge.mapper.AccountEntityResponseMapper;
import com.gbm.challenge.gbmchallenge.mapper.TransactionEntityResponseMapper;
import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.model.entities.TransactionEntity;
import com.gbm.challenge.gbmchallenge.model.response.CreateAccountResponse;
import com.gbm.challenge.gbmchallenge.model.response.CurrentBalance;
import com.gbm.challenge.gbmchallenge.model.response.SendOrderResponse;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;


@UtilityClass
public class MapperResponseFactory {

    private static final Map<String, ModelMapper> map = Map.ofEntries(
            entry(buildKey(AccountEntity.class.getName(), CreateAccountResponse.class.getName()),
                    new AccountEntityResponseMapper()),
            entry(buildKey(TransactionEntity.class.getName(), SendOrderResponse.class.getName()),
                    new TransactionEntityResponseMapper())
    );

    private static String buildKey(String clazzS, String clazzD) {
        return String.format("%s~%s", clazzS, clazzD);
    }

    public static <D> D createPositiveResponse(Object source, Class<D> destinationType) {
        String key = buildKey(source.getClass().getName(), destinationType.getName());
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException("There is no mapping between the desired classes");
        }
        return map.get(key).map(source, destinationType);
    }

    public static SendOrderResponse createNegativeResponse(String exceptionCode, String cash) {
        CurrentBalance currentBalance = new CurrentBalance(Double.valueOf(cash), List.of());
        return new SendOrderResponse(currentBalance, List.of(exceptionCode));
    }
}
