package com.tosan.card.dto;

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
public class TemporaryBankCard {

     String name;
     String number;
     int cvv2;
     LocalDate expirationDate;
     int passcode;
     boolean block ;
     boolean changedPasscode ;
     Long cardBalance;

}
