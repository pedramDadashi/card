package com.tosan.card.base.repository;

import com.tosan.card.base.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;


public interface BaseRepository<E extends BaseEntity<ID>, ID extends Serializable>
        extends JpaRepository<E,ID> {


}
