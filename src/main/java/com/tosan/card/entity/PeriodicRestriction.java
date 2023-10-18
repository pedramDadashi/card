package com.tosan.card.entity;


import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class PeriodicRestriction extends Restriction {

    @Override
    public void deductionFromRemainingAmount(Long amount) {
        super.setRemainingAmountFromRestriction(
                super.getRemainingAmountFromRestriction() - amount);
    }

    @Override
    public boolean updateRestriction() {
        return (super.getNumberOfDaysLeftRestriction() % super.getPeriodDays()) == 0 ;
    }
}
