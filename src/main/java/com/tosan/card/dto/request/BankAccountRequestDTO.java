package com.tosan.card.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class BankAccountRequestDTO {

    @NotBlank(message = "Name cannot be blank")
    String accountName;
    @NotBlank(message = "Name cannot be blank")
    String bankName;
    @Min(1111)
    @Max(999999)
    int branchCode;
    @Min(100000000L)
    @Max(999999999999L)
    Long accountNumber;
    @Min(10000000)
    @Max(Long.MAX_VALUE)
    Long balance;

}
