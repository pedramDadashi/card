package com.tosan.card.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class TemporaryBankCard {

     String name;
     String number;
     String cvv2;
     LocalDate expirationDate;
     String passcode;
     boolean block ;
     boolean changedPasscode ;
     Long cardBalance;

     public TemporaryBankCard(String name, String number, String cvv2, LocalDate expirationDate,
                              String passcode, Long cardBalance) {
          this.name = name;
          this.number = number;
          this.cvv2 = cvv2;
          this.expirationDate = expirationDate;
          this.passcode = passcode;
          this.block = true;
          this.changedPasscode = false;
          this.cardBalance = cardBalance;
     }
}
