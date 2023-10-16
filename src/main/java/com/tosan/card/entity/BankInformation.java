package com.tosan.card.entity;


import com.tosan.card.base.entity.BaseEntity;
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
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class BankInformation extends BaseEntity<Long> {

    String bankName;
    String branchName;
    int branchCode;
    int accountNumber;
    LocalDate openingDate;
    boolean block;
    String nationalCodeOfTheAccountHolder;
    Long balance;

    public BankInformation(String bankName, int accountNumber,
                            String nationalCode) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.nationalCodeOfTheAccountHolder = nationalCode;
    }
}
