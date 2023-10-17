package com.tosan.card.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Name cannot be blank")
    String firstname;
    @NotBlank(message = "Name cannot be blank")
    String lastname;
    @Email(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"
            , message = "Email should be valid")
    String email;
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
            message = "Password must be stronger")
    String password;
    @Size(min = 10, max = 10, message = "National code must be 10 digits")
    String nationalCode;

}
