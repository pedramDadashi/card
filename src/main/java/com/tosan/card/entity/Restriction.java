package com.tosan.card.entity;

import com.tosan.card.base.entity.BaseEntity;
import com.tosan.card.entity.enumuration.Period;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = true)
public abstract class Restriction extends BaseEntity<Long> {

    String name;
    @Enumerated(value = EnumType.STRING)
    Period period;
    int periodDays;
    int periodRepeat;
    int numberOfDaysLeftRestriction;
    LocalDate periodStartDate;
    Long amountRestriction;
    Long remainingAmountFromRestriction;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    Client client;

    public abstract void deductionFromRemainingAmount (Long amount);

    public abstract boolean updateRestriction();
}
