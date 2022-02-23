package com.gbm.challenge.gbmchallenge.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "TblTransaction")
@Getter
@Setter
@NoArgsConstructor
public class TransactionEntity {

    @Id @Column(name = "transaction_id") private UUID transactionId;
    @Column(name = "timestmp") private Timestamp timestamp;
    @Column(name = "success") private boolean success;
    @OneToMany(mappedBy = "transaction") private Set<TransactionDetailEntity> transactionDetails;
    @ManyToOne @JoinColumn(name = "account_id") private AccountEntity account;

    public TransactionEntity(AccountEntity account, Timestamp timestamp) {
        this.transactionId = UUID.randomUUID();
        this.account = account;
        this.timestamp = timestamp;
        this.success = true;
        transactionDetails = new HashSet<>();
    }
}
