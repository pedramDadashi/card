package com.tosan.card.entity;


import com.tosan.card.base.entity.BaseEntity;
import com.tosan.card.entity.enumuration.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Transaction extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    Card card;
    int destinationAccountNumber;
    Long amountTransaction;
    Long balance;
    LocalDateTime timeTransaction;
    @Enumerated(value = EnumType.STRING)
    TransactionStatus transactionStatus;
    Long trackingNumber;


}
