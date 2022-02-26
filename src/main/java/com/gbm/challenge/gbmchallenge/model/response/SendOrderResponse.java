package com.gbm.challenge.gbmchallenge.model.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gbm.challenge.gbmchallenge.exception.business.BusinessException;
import com.gbm.challenge.gbmchallenge.model.entities.TransactionDetailEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang.util.Validate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@RequiredArgsConstructor
public class SendOrderResponse {


    @JsonAlias("current_balance") private CurrentBalance currentBalance;
    @JsonAlias("business_errors") @Setter private List<BusinessException> exceptions;

    public void setCash(Double cash) {
        if (Objects.isNull(currentBalance)) {
            currentBalance = new CurrentBalance();
        }
        this.currentBalance.setCash(cash);
    }

    public void setIssuerResponse(Set<TransactionDetailEntity> transactions) {
        Validate.notNull(transactions, "Transactions cannot be null");
        this.currentBalance.setIssuers(Collections.emptyList());
        for (TransactionDetailEntity transactionDetail : transactions) {
            IssuerResponse issuerResponse = new IssuerResponse(transactionDetail.getIssuer().getName(),
                    transactionDetail.getQuantity(), transactionDetail.getPrice());
            this.currentBalance.getIssuers().add(issuerResponse);
        }
    }

    @Setter
    @Getter
    protected static final class CurrentBalance {
        @JsonAlias("cash") Double cash;
        @JsonAlias("issuers") List<IssuerResponse> issuers;
    }



}
