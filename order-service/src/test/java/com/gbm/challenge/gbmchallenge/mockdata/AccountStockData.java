package com.gbm.challenge.gbmchallenge.mockdata;

import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.model.entities.AccountStockEntity;
import com.gbm.challenge.gbmchallenge.model.entities.IssuerEntity;

import java.util.Set;
import java.util.stream.LongStream;

import static com.gbm.challenge.gbmchallenge.mockdata.FilterData.getAccountStockByIssuerId;
import static com.gbm.challenge.gbmchallenge.mockdata.FilterData.getTransactionDetailsByIssuerId;
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
        issuerData.AMZN.setAccountStocks(getTransactionDetails(
                getAccountStockByIssuerId(issuerData.AMZN.getIssuerId())));
        issuerData.AAPL.setAccountStocks(getTransactionDetails(
                getAccountStockByIssuerId(issuerData.AAPL.getIssuerId())));
        issuerData.NFTX.setAccountStocks(getTransactionDetails(
                getAccountStockByIssuerId(issuerData.NFTX.getIssuerId())));
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
