package com.tosan.card.dto.response;

import com.tosan.card.dto.enumuration.PeriodDTO;
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
public class RestrictionResponseDTO {

     String name;
     Long amountRestriction;
     String useType;
     LocalDate validityDateOfTheRestriction;
     int validityDaysOfTheRestriction;
     Long clientId;
     PeriodDTO period;
}
