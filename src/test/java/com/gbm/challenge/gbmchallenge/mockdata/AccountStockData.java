package com.gbm.challenge.gbmchallenge.mockdata;

import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.model.entities.AccountStockEntity;
import com.gbm.challenge.gbmchallenge.model.entities.IssuerEntity;

import java.util.Set;
import java.util.stream.LongStream;

import static com.gbm.challenge.gbmchallenge.mockdata.FilterData.getTransactionDetailsByIssuerId;
import static com.gbm.challenge.gbmchallenge.mockdata.IssuerData.*;
import static org.mockito.Mockito.mock;

public class AccountStockData extends BaseQueryableDummy<AccountStockEntity> {

    private static final AccountStockData INSTANCE = new AccountStockData();

    private static final Set<AccountStockEntity> ACCOUNT_STOCK_ENTITIES = Set.of(
            new AccountStockEntity(calculateStockQty(AMZN), AccountData.getDummyAccount(), AMZN),
            new AccountStockEntity(calculateStockQty(NFTX), AccountData.getDummyAccount(), NFTX),
            new AccountStockEntity(calculateStockQty(AAPL), AccountData.getDummyAccount(), AAPL)
    );

    private AccountStockData() { }

    public static AccountStockData getInstance() {
        return INSTANCE;
    }

    public static AccountStockEntity getDummy() {
        return new AccountStockEntity(10L, mock(AccountEntity.class),
                mock(IssuerEntity.class));
    }

    @Override
    public Set<AccountStockEntity> getTransactionDetails() {
        return ACCOUNT_STOCK_ENTITIES;
    }

    private static Long calculateStockQty(IssuerEntity issuer) {
        return TransactionDetailData.getInstance()
                .getTransactionDetails(getTransactionDetailsByIssuerId(issuer.getIssuerId()))
                .stream().flatMapToLong(x-> LongStream.of(x.getQuantity() * x.getOperation().getStockFactor()))
                .sum()
        ;
    }
}
