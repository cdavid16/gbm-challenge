package com.gbm.challenge.gbmchallenge.model.entities;

import com.gbm.challenge.gbmchallenge.wrapper.UUIDSource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_transaction")
public class TransactionEntity {

    @Id @Column(name = "transaction_id") private String transactionId;
    @Column(name = "timestmp") private Long timestamp;
    @Column(name = "success") private boolean success;
    @OneToMany(mappedBy = "transaction") private Set<TransactionDetailEntity> transactionDetails;
    @ManyToOne @JoinColumn(name = "account_id") private AccountEntity account;

    public TransactionEntity(final AccountEntity account, final Long timestamp) {
        this.transactionId = UUIDSource.generateRandom().getRandomUUID().toString();
        this.account = account;
        this.timestamp = timestamp;
        this.success = true;
        transactionDetails = new HashSet<>();
    }
}
