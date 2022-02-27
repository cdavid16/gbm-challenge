package com.gbm.challenge.gbmchallenge.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gbm.challenge.gbmchallenge.model.entities.TransactionDetailEntity;
import lombok.*;
import org.apache.commons.lang.util.Validate;

import java.util.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SendOrderResponse {


    @JsonProperty("current_balance") private CurrentBalance currentBalance;
    @JsonProperty("business_errors") @Setter private List<String> exceptions;

    public void setCash(Double cash) {
        if (Objects.isNull(currentBalance)) {
            currentBalance = new CurrentBalance();
        }
        this.currentBalance.setCash(cash);
    }

    public void setIssuerResponse(Set<TransactionDetailEntity> transactions) {
        Validate.notNull(transactions, "Transactions cannot be null");
        this.currentBalance.setIssuers(new ArrayList<>());
        for (TransactionDetailEntity transactionDetail : transactions) {
            IssuerResponse issuerResponse = new IssuerResponse(transactionDetail.getIssuer().getName(),
                    transactionDetail.getQuantity(), transactionDetail.getPrice());
            this.currentBalance.getIssuers().add(issuerResponse);
        }
    }
}
