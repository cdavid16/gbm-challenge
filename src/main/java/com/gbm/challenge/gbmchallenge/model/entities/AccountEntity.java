package com.gbm.challenge.gbmchallenge.model.entities;

import com.gbm.challenge.gbmchallenge.wrapper.UUIDSource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "tbl_account")
public class AccountEntity {

    @Id @Column(name = "account_id") private String accountId;
    @Column(name = "balance") private Double balance;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL) private Set<AccountStockEntity> accountStocks;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL) private Set<TransactionEntity> transactions;

    public AccountEntity(final Double balance) {
        this.accountId = UUIDSource.generateRandom().getRandomUUID().toString();
        this.balance = balance;
    }
}
