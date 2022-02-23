package com.gbm.challenge.gbmchallenge.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.UUID;

@Entity(name = "TblAccountStock")
@Getter
@Setter
@NoArgsConstructor
@IdClass(AccountStockEntity.AccountStockEntityId.class)
public class AccountStockEntity {

    @Column(name = "quantity") private BigInteger quantity;
    @Id @JsonIgnore @ManyToOne @JoinColumn( name = "account_id") AccountEntity account;
    @Id @JsonIgnore @ManyToOne @JoinColumn( name = "issuer_id") IssuerEntity issuer;

    public AccountStockEntity(final AccountEntity account, final IssuerEntity issuer, final  BigInteger quantity) {
        this.account = account;
        this.issuer = issuer;
        this.quantity = quantity;
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    protected static class AccountStockEntityId implements Serializable {
        private final AccountEntity account;
        private final IssuerEntity issuer;
    }

}
