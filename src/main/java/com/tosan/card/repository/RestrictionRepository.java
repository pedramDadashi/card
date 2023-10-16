package com.tosan.card.repository;



import com.tosan.card.base.repository.BaseRepository;
import com.tosan.card.entity.Restriction;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestrictionRepository extends BaseRepository<Restriction, Long> {


    Optional<Restriction> findByName(String name);
}
