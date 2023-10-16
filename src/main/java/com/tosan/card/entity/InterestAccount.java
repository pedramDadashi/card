package com.tosan.card.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class InterestAccount extends BankAccount {

    float interestRate;

    public InterestAccount(String name, BankInformation bankInformation, float interestRate) {
        super(name, bankInformation);
        this.interestRate = interestRate;
    }
}
