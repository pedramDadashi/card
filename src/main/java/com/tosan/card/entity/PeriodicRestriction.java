package com.tosan.card.entity;


import com.tosan.card.entity.enumuration.Period;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class PeriodicRestriction extends Restriction {

    @Enumerated(value = EnumType.STRING)
    Period period;
    int numberOfPeriod;
    int numberOfPeriodDays;
    boolean saveTheAmountFromPreviousPeriod;



}
