package com.tosan.card.dto.request;

import com.tosan.card.dto.enumuration.PeriodDTO;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class PeriodicRestrictionRequestDTO {

    @NotBlank(message = "Name cannot be blank")
    String name;
    @Enumerated(value = EnumType.STRING)
    PeriodDTO period;
    @Min(0)
    @Max(90)
    int periodDays;
    @Min(1)
    @Max(364)
    int periodRepeat;
    @Min(10000000)
    @Max(Long.MAX_VALUE)
    Long amountRestriction;

}
