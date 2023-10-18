package com.tosan.card.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class ChangeCardPasscodeDTO {
    @NotNull
    @Pattern(regexp = "^[1-9][0-9]{15}$")
    String cardNumber;
    @NotNull
    @Pattern(regexp = "^[0-9]{4}$")
    String newPasscode;

}
