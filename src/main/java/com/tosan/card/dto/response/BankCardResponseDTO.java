package com.tosan.card.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class BankCardResponseDTO {

    String name;
    String number;
    int cvv2;
    LocalDate expirationDate;
    boolean block;
    boolean changePassword;
    Long cardBalance;
    Long cardAmountRestriction;
    String bankAccountName;
    Long bankAccountNumber;

}
