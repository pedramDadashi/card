package com.tosan.card.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ClientResponseDTO {

    Long clientId;
    String firstname;
    String lastname;
    String emailAddress;
    Boolean isActive;
    Long credit;
    int numberOfOperation;
    int numberOfDoneOperation;
    @JsonFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss")
    LocalDateTime registrationTime;


}
