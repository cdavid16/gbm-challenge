package com.gbm.challenge.gbmchallenge.model.entities;

import com.gbm.challenge.gbmchallenge.wrapper.UUIDSource;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_issuer")
public class IssuerEntity {

    @Id @Column(name = "issuer_id") private String issuerId;
    @Column(name = "name") private String name;
    @OneToMany(mappedBy = "issuer") private Set<TransactionDetailEntity> transactionDetails;
    @OneToMany(mappedBy = "issuer") private Set<AccountStockEntity> accountStocks;

    public IssuerEntity(final String name) {
        this.issuerId = UUIDSource.generateRandom().getRandomUUID().toString();
        this.name = name;
    }

}
