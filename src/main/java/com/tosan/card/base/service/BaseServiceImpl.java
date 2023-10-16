package com.tosan.card.base.service;

import com.tosan.card.base.entity.BaseEntity;
import com.tosan.card.base.repository.BaseRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public abstract class BaseServiceImpl<E extends BaseEntity<ID>, ID extends Serializable
        , R extends BaseRepository<E, ID>>
        implements BaseService<E, ID> {

    protected final R repository;

    protected BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public boolean isExistById(ID id) {
        return repository.existsById(id);
    }

    @Override
    @Transactional
    public void save(E e) {
        repository.save(e);
    }

    @Override
    @Transactional
    public void delete(E e) {
        repository.delete(e);
    }

    @Override
    public Optional<E> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }
}
