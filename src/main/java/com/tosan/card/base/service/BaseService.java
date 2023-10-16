package com.tosan.card.base.service;


import com.tosan.card.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public interface BaseService<E extends BaseEntity<ID>, ID extends Serializable> {

    void save(E e);

    void delete(E e);

    Optional<E> findById(ID id);

    List<E> findAll();

    boolean isExistById(ID id);

}
