package com.tosan.card.entity;


import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@NoArgsConstructor

@FieldDefaults(level = PRIVATE)
public class RegularClient extends Client {

    public RegularClient(String firstname, String lastname, String email,
                         String nationalCode, String password) {
        super(firstname, lastname, email, nationalCode, password);
    }


}
