package com.tosan.card.service.Impl;



import com.tosan.card.base.service.BaseServiceImpl;
import com.tosan.card.entity.Restriction;
import com.tosan.card.repository.RestrictionRepository;
import com.tosan.card.service.RestrictionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RestrictionServiceImpl extends BaseServiceImpl<Restriction, Long, RestrictionRepository>
        implements RestrictionService {


    public RestrictionServiceImpl(RestrictionRepository repository) {
        super(repository);
    }

    @Override
    public Optional<Restriction> findByName(String name) {
        return repository.findByName(name);
    }
}
