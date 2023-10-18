package com.tosan.card.entity;


import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
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
