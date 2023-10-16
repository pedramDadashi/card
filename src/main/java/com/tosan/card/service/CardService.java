package com.tosan.card.service;




import com.tosan.card.base.service.BaseService;
import com.tosan.card.entity.Card;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CardService extends BaseService<Card,Long> {

    Optional<Card> findByName(String name);

    Optional<Card> findByNumber(String cardNumber);
}
