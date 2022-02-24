package com.gbm.challenge.gbmchallenge.model.entities;

import com.gbm.challenge.gbmchallenge.wrapper.UUIDSource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_account")
public class AccountEntity {

    @Id @Column(name = "account_id") private String accountId;
    @Column(name = "balance") private Double balance;
    @OneToMany(mappedBy = "account") private Set<AccountStockEntity> accountStocks;
    @OneToMany(mappedBy = "account") private Set<TransactionEntity> transactions;

    public AccountEntity(final Double balance) {
        this.accountId = UUIDSource.generateRandom().getRandomUUID().toString();
        this.balance = balance;
    }
}
