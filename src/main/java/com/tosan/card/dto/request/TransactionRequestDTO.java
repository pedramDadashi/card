package com.tosan.card.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class TransactionRequestDTO {

    @NotNull
    @Pattern(regexp = "^[1-9][0-9]{15}$")
    String cardNumber;
    @NotNull
    @Min(100000L)
    @Max(9999999999L)
    Long destinationAccountNumber;
    @NotNull
    @Min(10000L)
    @Max(Long.MAX_VALUE)
    Long amountTransaction;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    LocalDateTime timeTransaction;

}
