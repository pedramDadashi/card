package com.tosan.card.entity;


import com.tosan.card.entity.enumuration.UseType;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@NoArgsConstructor

@FieldDefaults(level = PRIVATE)
public class NormalRestriction extends Restriction {

    public NormalRestriction(String name, Long amountRestriction, UseType useType,
                             LocalDate validityDateOfTheRestriction, int validityDaysOfTheRestriction) {
        super(name, amountRestriction, useType, validityDateOfTheRestriction
                , validityDaysOfTheRestriction);
    }

}
