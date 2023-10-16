package com.tosan.card.entity;


import com.tosan.card.base.entity.BaseEntity;
import com.tosan.card.entity.Card;
import com.tosan.card.entity.enumuration.TransactionStatus;
import com.tosan.card.entity.enumuration.TransactionType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    int DestinationAccountNumber;

    Long TransactionAmount;

    Long balance;

    TransactionType transactionType;

    LocalDateTime transactionDateTime;

    TransactionStatus transactionStatus;

    Long trackingNumber;


}
