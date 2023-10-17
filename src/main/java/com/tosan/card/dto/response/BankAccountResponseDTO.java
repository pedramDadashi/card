package com.tosan.card.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class BankAccountResponseDTO {

     String accountName;
     String bankName;
     int branchCode;
     Long accountNumber;
     Long balance;

}
