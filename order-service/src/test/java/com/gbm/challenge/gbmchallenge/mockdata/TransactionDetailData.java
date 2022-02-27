package com.gbm.challenge.gbmchallenge.mockdata;

import com.gbm.challenge.gbmchallenge.model.entities.IssuerEntity;
import com.gbm.challenge.gbmchallenge.model.entities.TransactionDetailEntity;
import com.gbm.challenge.gbmchallenge.model.entities.TransactionEntity;

import java.util.Set;

import static com.gbm.challenge.gbmchallenge.enums.OperationEnum.BUY;
import static com.gbm.challenge.gbmchallenge.enums.OperationEnum.SELL;
import static com.gbm.challenge.gbmchallenge.mockdata.FilterData.getTransactionDetailsByIssuerId;
import static com.gbm.challenge.gbmchallenge.mockdata.FilterData.getTransactionDetailsByTransactionId;

public class TransactionDetailData extends BaseQueryableDummy<TransactionDetailEntity> {

    private final Set<TransactionDetailEntity> transactionDetailEntities;

    public TransactionDetailData(final TransactionData transactionData, final IssuerData issuerData){
        transactionDetailEntities = Set.of(
                new TransactionDetailEntity(transactionData.TRANSACTION_1, issuerData.AMZN, BUY, 10L, 1010.50),
                new TransactionDetailEntity(transactionData.TRANSACTION_1, issuerData.NFTX, BUY, 15L, 950.00),
                new TransactionDetailEntity(transactionData.TRANSACTION_1, issuerData.AMZN, BUY, 100L, 535.00),
                new TransactionDetailEntity(transactionData.TRANSACTION_2, issuerData.AMZN, SELL, 30L, 600.00),
                new TransactionDetailEntity(transactionData.TRANSACTION_2, issuerData.AAPL, BUY, 5L, 2000.00),
                new TransactionDetailEntity(transactionData.TRANSACTION_2, issuerData.NFTX, BUY, 20L, 935.00),
                new TransactionDetailEntity(transactionData.TRANSACTION_3, issuerData.NFTX, SELL, 35L, 1100.00),
                new TransactionDetailEntity(transactionData.TRANSACTION_3, issuerData.AMZN, SELL, 5L, 1500.00),
                new TransactionDetailEntity(transactionData.TRANSACTION_3, issuerData.AAPL, BUY, 5L, 1900.00)
        );
        createRelationships(transactionData.getDummyTransactions(), issuerData.issuers);
    }

    private void createRelationships(Set<TransactionEntity> transactions, Set<IssuerEntity> issuers) {
        for(TransactionEntity transaction : transactions) {
            transaction.setTransactionDetails(
                    getTransactionDetails(getTransactionDetailsByTransactionId(transaction.getTransactionId())));
        }
        for(IssuerEntity issuer : issuers) {
            issuer.setTransactionDetails(
                    getTransactionDetails(getTransactionDetailsByIssuerId(issuer.getIssuerId())));
        }
    }

    public TransactionDetailEntity getTransactionDetail() {
        return new TransactionDetailEntity();
    }

    @Override
    public Set<TransactionDetailEntity> getDetails() {
        return transactionDetailEntities;
    }

}
