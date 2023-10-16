package com.tosan.card.dto.request;


import com.tosan.card.dto.enumuration.UseTypeDTO;
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
public abstract class RestrictionRequestDTO {

    String name;
    Long amountRestriction;
    UseTypeDTO useType;
    LocalDate validityDateOfTheRestriction;
    int validityDaysOfTheRestriction;
}
