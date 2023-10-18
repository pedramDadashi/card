package com.tosan.card.entity;

import com.tosan.card.entity.enumuration.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = true)
public abstract class Client extends Users {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    List<BankAccount> bankAccountList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    List<Restriction> restrictionList = new ArrayList<>();

    protected Client(String firstname, String lastname, String email,
                  String nationalCode, String password) {
        super(firstname, lastname, email, nationalCode, password, Role.CLIENT);
    }

    public void addBankAccount(BankAccount bankAccount) {
        this.bankAccountList.add(bankAccount);
    }

    public void addRestriction(Restriction restriction) {
        this.restrictionList.add(restriction);
    }

}
