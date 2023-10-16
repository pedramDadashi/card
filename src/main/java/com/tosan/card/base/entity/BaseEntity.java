package com.tosan.card.base.entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

import static jakarta.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.PRIVATE;

@Data
@MappedSuperclass
@FieldDefaults(level = PRIVATE)
public abstract class BaseEntity<ID extends Serializable> implements Serializable {
    @Id
    @GeneratedValue(strategy = AUTO)
    ID id;

}

