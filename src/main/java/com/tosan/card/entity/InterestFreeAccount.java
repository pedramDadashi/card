package com.tosan.card.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class InterestFreeAccount extends BankAccount {

    @Override
    public void deductionBalanceAccount(Long amount) {
        super.setBalance(super.getBalance() - amount);
    }
}
