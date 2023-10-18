package com.tosan.card.entity;

import com.tosan.card.base.entity.BaseEntity;
import com.tosan.card.entity.enumuration.Period;
import jakarta.persistence.*;
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
public abstract class Restriction extends BaseEntity<Long> {

    String name;
    @Enumerated(value = EnumType.STRING)
    Period period;
    int periodDays;
    int periodRepeat;
    Long amountRestriction;
    Long remainingAmountFromRestriction;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    Client client;

    protected Restriction(String name, Period period, int periodDays,
                       int periodRepeat, Long amountRestriction) {
        this.name = name;
        this.period = period;
        this.periodDays = periodDays;
        this.periodRepeat = periodRepeat;
        this.amountRestriction = amountRestriction;
        this.remainingAmountFromRestriction = amountRestriction;
    }

    public abstract void deductionFromRemainingAmount (Long amount);
}
