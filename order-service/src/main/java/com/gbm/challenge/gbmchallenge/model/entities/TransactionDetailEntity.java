package com.gbm.challenge.gbmchallenge.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gbm.challenge.gbmchallenge.enums.OperationEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@IdClass(TransactionDetailEntity.TransactionDetailId.class)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_transaction_detail")
public class TransactionDetailEntity {

    @Id @ManyToOne @JoinColumn(name = "transaction_id") @ToString.Exclude private TransactionEntity transaction;
    @Id @ManyToOne @JoinColumn(name = "issuer_id") @ToString.Exclude private IssuerEntity issuer;
    @Column(name = "operation") @Enumerated(EnumType.STRING) private OperationEnum operation;
    @Column(name = "quantity") private Long quantity;
    @Column(name = "price") private Double price;

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    protected static class TransactionDetailId implements Serializable {
        private IssuerEntity issuer;
        private TransactionEntity transaction;
    }

}
