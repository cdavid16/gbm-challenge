package com.gbm.challenge.gbmchallenge.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.UUID;

@Entity(name = "TblIssuer")
@Getter
@Setter
@NoArgsConstructor
public class IssuerEntity {

    @Id @Column(name = "issuer_id") private UUID issuerId;
    @Column(name = "name") private String name;
    @OneToMany(mappedBy = "issuer") private Set<TransactionDetailEntity> transactionDetails;
    @OneToMany(mappedBy = "issuer") private Set<AccountStockEntity> accountStocks;

    public IssuerEntity(String name) {
        this.issuerId = UUID.randomUUID();
        this.name = name;
    }

}
