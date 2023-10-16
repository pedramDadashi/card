package com.tosan.card.util;


import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.regex.Pattern;

@Component
public class Validation {


    public boolean checkNationalCode(String nationalCode) {
        String nationalCodeRegex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if (!Pattern.matches(nationalCodeRegex, nationalCode))
            throw new ValidationException("the format of the national code is incorrect!");
        return true;
    }

    public boolean checkEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if (!Pattern.matches(emailRegex, email))
            throw new ValidationException("the format of the email is incorrect!");
        return true;
    }


    public boolean checkPassword(String password) {
        String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        if (!Pattern.matches(passwordRegex, password))
            throw new ValidationException("the format of the password is incorrect!");
        return true;
    }


    public boolean checkCardNumber(String cardNumber) {
        if (!(cardNumber.length() == 16))
            throw new ValidationException("this card number is less 16 digit!");
        return true;
    }
}
