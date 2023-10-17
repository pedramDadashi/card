package com.tosan.card.entity;


import com.tosan.card.base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public abstract class Card extends BaseEntity<Long> {

    String name;
    String number;
    int cvv2;
    LocalDate expirationDate;
    int passcode;
    boolean block ;
    boolean changedPasscode ;
    Long cardBalance;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankAccount_id")
    BankAccount bankAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restriction_id")
    Restriction restriction;

}
