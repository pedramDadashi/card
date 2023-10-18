package com.tosan.card.dto.response;

import com.tosan.card.dto.enumuration.PeriodDTO;
import com.tosan.card.entity.enumuration.Period;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
     @Enumerated(value = EnumType.STRING)
     PeriodDTO period;
     int periodDays;
     int periodRepeat;
     LocalDate periodStartDate;
     Long amountRestriction;

}
