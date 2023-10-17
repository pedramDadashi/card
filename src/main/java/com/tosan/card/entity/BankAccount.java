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

    String accountName;
    String bankName;
    int branchCode;
    Long accountNumber;
    Long balance;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    Client client;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bankAccount")
    List<Card> cardList;

    protected BankAccount(String accountName, String bankName, int branchCode,
                          Long accountNumber, Long balance) {
        this.accountName = accountName;
        this.bankName = bankName;
        this.branchCode = branchCode;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void addCard(Card card) {
        this.cardList.add(card);
    }
}
