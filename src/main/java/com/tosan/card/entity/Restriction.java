package com.tosan.card.entity;

import com.tosan.card.base.entity.BaseEntity;
import com.tosan.card.entity.enumuration.UseType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public abstract class Restriction extends BaseEntity<Long> {

    String name;
    Long amountRestriction;
    @Enumerated(value = EnumType.STRING)
    UseType useType;
    LocalDate validityDateOfTheRestriction;
    int validityDaysOfTheRestriction;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    Client client;

    public Restriction(String name, Long amountRestriction, UseType useType) {
        this.name = name;
        this.amountRestriction = amountRestriction;
        this.useType = useType;
    }

    public Restriction(String name, Long amountRestriction,
                       UseType useType, LocalDate validityDateOfTheRestriction,
                       int validityDaysOfTheRestriction) {
        this.name = name;
        this.amountRestriction = amountRestriction;
        this.useType = useType;
        this.validityDateOfTheRestriction = validityDateOfTheRestriction;
        this.validityDaysOfTheRestriction = validityDaysOfTheRestriction;
    }
}
