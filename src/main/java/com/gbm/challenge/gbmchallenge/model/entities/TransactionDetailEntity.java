package com.gbm.challenge.gbmchallenge.model.entities;

import com.gbm.challenge.gbmchallenge.enums.TransactionEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@IdClass(TransactionDetailEntity.TransactionDetailId.class)
@AllArgsConstructor
@Entity
@Table(name = "tbl_transaction_detail")
public class TransactionDetailEntity {

    @Id @ManyToOne @JoinColumn(name = "transaction_id") private final TransactionEntity transaction;
    @Id @ManyToOne @JoinColumn(name = "issuer_id") private final IssuerEntity issuer;
    @Column(name = "operation") private final TransactionEnum operation;
    @Column(name = "quantity") private final Long quantity;
    @Column(name = "price") private final Double price;

    @Getter
    @Setter
    @RequiredArgsConstructor
    protected static class TransactionDetailId implements Serializable {
        private final IssuerEntity issuer;
        private final TransactionEntity transaction;
    }

}
