package com.tosan.card.service.Impl;


import com.tosan.card.base.service.BaseServiceImpl;
import com.tosan.card.entity.Card;
import com.tosan.card.repository.CardRepository;
import com.tosan.card.service.CardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CardServiceImpl extends BaseServiceImpl<Card, Long, CardRepository>
        implements CardService {


    public CardServiceImpl(CardRepository repository) {
        super(repository);
    }

    @Override
    public Optional<Card> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Optional<Card> findByNumber(String cardNumber) {
        return repository.findByNumber(cardNumber);
    }
}
