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
public class BankAccountResponseDTO {

     String name;
     String bankName;
     String branchName;
     int branchCode;
     int accountNumber;
     LocalDate openingDate;

}
