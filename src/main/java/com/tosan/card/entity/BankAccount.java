package com.tosan.card.entity;

import com.tosan.card.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public abstract class BankAccount extends BaseEntity<Long> {

    String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    Client client;
    @OneToOne
    BankInformation bankInformation;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bankAccount")
    List<Card> cardList;

    protected BankAccount(String name, BankInformation bankInformation) {
        this.name = name;
        this.bankInformation = bankInformation;
    }
}
