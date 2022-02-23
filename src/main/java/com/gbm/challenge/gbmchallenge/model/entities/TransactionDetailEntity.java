package com.gbm.challenge.gbmchallenge.model.entities;

import com.gbm.challenge.gbmchallenge.enums.TransactionEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

@Entity(name = "TblTransactionDetail")
@Getter
@Setter
@NoArgsConstructor
@IdClass(TransactionDetailEntity.TransactionDetailId.class)
public class TransactionDetailEntity {

    @Column(name = "operation") private TransactionEnum operation;
    @Column(name = "quantity") private BigInteger quantity;
    @Column(name = "price") private BigDecimal price;
    @Id @ManyToOne @JoinColumn(name = "issuer_id") IssuerEntity issuer;
    @Id @ManyToOne @JoinColumn(name = "transaction_id") TransactionEntity transaction;

    @Getter
    @Setter
    @RequiredArgsConstructor
    protected static class TransactionDetailId implements Serializable {
        private final IssuerEntity issuer;
        private final TransactionEntity transaction;
    }

}
