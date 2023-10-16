package com.tosan.card.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@NoArgsConstructor

@FieldDefaults(level = PRIVATE)
public class CreditCard extends Card {

    public CreditCard(String name, String number, int cvv2, LocalDate expirationDate,
                      int password, boolean block, Long cardBalance,
                      BankAccount bankAccount, Restriction restriction) {
        super(name, number, cvv2, expirationDate, password, block, false,
                cardBalance, bankAccount, restriction);
    }
}
