package com.gbm.challenge.gbmchallenge.model.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_account_stock")
@IdClass(AccountStockEntity.AccountStockEntityId.class)
public class AccountStockEntity {

    @Column(name = "quantity") private Long quantity;
    @Id @ToString.Exclude @ManyToOne @JoinColumn( name = "account_id") AccountEntity account;
    @Id @ToString.Exclude @ManyToOne @JoinColumn( name = "issuer_id") IssuerEntity issuer;

    public AccountStockEntity(final AccountEntity account, final IssuerEntity issuer, final  Long quantity) {
        this.account = account;
        this.issuer = issuer;
        this.quantity = quantity;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class AccountStockEntityId implements Serializable {
        private AccountEntity account;
        private IssuerEntity issuer;
    }

}
