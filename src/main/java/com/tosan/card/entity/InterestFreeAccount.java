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
@FieldDefaults(level = PRIVATE)
public class InterestFreeAccount extends BankAccount {

}
