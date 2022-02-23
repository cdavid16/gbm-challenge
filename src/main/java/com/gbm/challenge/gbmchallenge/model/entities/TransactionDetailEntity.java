package com.gbm.challenge.gbmchallenge.model.entities;

import com.gbm.challenge.gbmchallenge.enums.TransactionEnum;
import lombok.*;

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
@IdClass(TransactionDetailEntity.TransactionDetailId.class)
@AllArgsConstructor
public class TransactionDetailEntity {

    @Id @ManyToOne @JoinColumn(name = "transaction_id") private final TransactionEntity transaction;
    @Id @ManyToOne @JoinColumn(name = "issuer_id") private final IssuerEntity issuer;
    @Column(name = "operation") private final TransactionEnum operation;
    @Column(name = "quantity") private final BigInteger quantity;
    @Column(name = "price") private final BigDecimal price;

    @Getter
    @Setter
    @RequiredArgsConstructor
    protected static class TransactionDetailId implements Serializable {
        private final IssuerEntity issuer;
        private final TransactionEntity transaction;
    }

}
