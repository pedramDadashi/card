package com.tosan.card.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public abstract class ClientRegistrationDTO {

    String firstname;
    String lastname;
    String email;
    String password;
    String nationalCode;

}
