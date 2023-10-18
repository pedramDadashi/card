package com.tosan.card.entity;


import com.tosan.card.base.entity.BaseEntity;
import jakarta.persistence.*;
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
    String cvv2;
    LocalDate expirationDate;
    String passcode;
    boolean block;
    boolean changedPasscode;
    Long cardBalance;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bankAccount_id")
    BankAccount bankAccount;
    @OneToOne
    Restriction restriction;

    public abstract void deductionFromCardBalance(Long amount);

}
