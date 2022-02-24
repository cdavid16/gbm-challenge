package com.gbm.challenge.gbmchallenge.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_account_stock")
@IdClass(AccountStockEntity.AccountStockEntityId.class)
public class AccountStockEntity {

    @Column(name = "quantity") private Long quantity;
    @Id @JsonIgnore @ManyToOne @JoinColumn( name = "account_id") AccountEntity account;
    @Id @JsonIgnore @ManyToOne @JoinColumn( name = "issuer_id") IssuerEntity issuer;

    public AccountStockEntity(final AccountEntity account, final IssuerEntity issuer, final  Long quantity) {
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
