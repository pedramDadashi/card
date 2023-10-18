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
public class ChangeAccountPasswordDTO {

    static final String emailRegexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
    @NotNull
    @Pattern(regexp = emailRegexp,
            message = "Password must be stronger")
    String newPassword;
    @NotNull
    @Pattern(regexp = emailRegexp,
            message = "Password must be stronger")
    String confirmNewPassword;

}
