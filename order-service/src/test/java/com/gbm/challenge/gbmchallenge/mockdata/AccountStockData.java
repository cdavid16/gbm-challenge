package com.gbm.challenge.gbmchallenge.mockdata;

import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.model.entities.AccountStockEntity;
import com.gbm.challenge.gbmchallenge.model.entities.IssuerEntity;
import com.gbm.challenge.gbmchallenge.model.entities.TransactionEntity;

import java.util.Set;
import java.util.stream.LongStream;

import static com.gbm.challenge.gbmchallenge.mockdata.FilterData.*;
import static org.mockito.Mockito.mock;

public class AccountStockData extends BaseQueryableDummy<AccountStockEntity> {

    private final Set<AccountStockEntity> ACCOUNT_STOCK_ENTITIES;

    public AccountStockData(final IssuerData issuerData,
                             final AccountData accountData,
                             final TransactionDetailData transactionDetailData) {
        ACCOUNT_STOCK_ENTITIES = Set.of(
                new AccountStockEntity(calculateStockQty(issuerData.AMZN, transactionDetailData),
                        accountData.getDummyAccount(), issuerData.AMZN),
                new AccountStockEntity(calculateStockQty(issuerData.NFTX, transactionDetailData),
                        accountData.getDummyAccount(), issuerData.NFTX),
                new AccountStockEntity(calculateStockQty(issuerData.AAPL, transactionDetailData),
                        accountData.getDummyAccount(), issuerData.AAPL)
        );
        createRelationships(issuerData.issuers, accountData.getDummyAccount());
    }

    private void createRelationships(Set<IssuerEntity> issuers, AccountEntity account) {
        for(IssuerEntity issuer : issuers) {
            issuer.setAccountStocks(
                    getTransactionDetails(getAccountStockByIssuerId(issuer.getIssuerId())));
        }
        account.setAccountStocks(ACCOUNT_STOCK_ENTITIES);
    }

    public static AccountStockEntity getDummy() {
        return new AccountStockEntity(10L, mock(AccountEntity.class),
                mock(IssuerEntity.class));
    }

    @Override
    public Set<AccountStockEntity> getDetails() {
        return this.ACCOUNT_STOCK_ENTITIES;
    }

    private static Long calculateStockQty(final IssuerEntity issuer,
                                          final TransactionDetailData transactionDetailData) {
        return transactionDetailData
                .getTransactionDetails(getTransactionDetailsByIssuerId(issuer.getIssuerId()))
                .stream().flatMapToLong(x-> LongStream.of(x.getQuantity() * x.getOperation().getStockFactor()))
                .sum()
        ;
    }
}
