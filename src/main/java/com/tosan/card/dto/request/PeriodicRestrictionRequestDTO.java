package com.tosan.card.dto.request;

import com.tosan.card.dto.enumuration.PeriodDTO;
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
public class PeriodicRestrictionRequestDTO extends RestrictionRequestDTO {

     PeriodDTO period;
     boolean saveTheAmountFromPreviousPeriod;

     public PeriodicRestrictionRequestDTO(String name, Long amountRestriction, UseTypeDTO useType,
                                          LocalDate validityDateOfTheRestriction, int validityDaysOfTheRestriction,
                                          PeriodDTO period, boolean saveTheAmountFromPreviousPeriod) {
          super(name, amountRestriction, useType, validityDateOfTheRestriction, validityDaysOfTheRestriction);
          this.period = period;
          this.saveTheAmountFromPreviousPeriod = saveTheAmountFromPreviousPeriod;
     }
}
