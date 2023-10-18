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

}
