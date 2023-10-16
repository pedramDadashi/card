package com.tosan.card.repository;



import com.tosan.card.base.repository.BaseRepository;
import com.tosan.card.entity.Card;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends BaseRepository<Card, Long> {

    Optional<Card> findByName(String name);

    Optional<Card> findByNumber(String cardNumber);
}
