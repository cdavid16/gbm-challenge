package com.gbm.challenge.gbmchallenge.model.entities;

import com.gbm.challenge.gbmchallenge.wrapper.UUIDSource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Entity(name = "TblAccount")
@Getter
@Setter
@NoArgsConstructor
public class AccountEntity {

    @Id @Column(name = "account_id") private UUID accountId;
    @Column(name = "balance") private BigDecimal balance;
    @OneToMany(mappedBy = "account") private Set<AccountStockEntity> accountStocks;
    @OneToMany(mappedBy = "account") private Set<TransactionEntity> transactions;

    public AccountEntity(final BigDecimal balance) {
        this.accountId = UUIDSource.generateRandom().getRandomUUID();
        this.balance = balance;
    }
}
