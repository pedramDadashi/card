package com.tosan.card.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class BankCardRequestDTO {

     @NotBlank(message = "Name cannot be blank")
     String cardName;
     @NotBlank(message = "Name cannot be blank")
     String accountName;
     @NotBlank(message = "Name cannot be blank")
     String restrictionName;

}
