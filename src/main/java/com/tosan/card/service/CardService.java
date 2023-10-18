package com.tosan.card.service;


import com.tosan.card.base.service.BaseService;
import com.tosan.card.dto.TemporaryBankCard;
import com.tosan.card.entity.Card;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CardService extends BaseService<Card, Long> {

    Optional<Card> findByName(String name);

    Optional<Card> findByNumber(String cardNumber);

    TemporaryBankCard buildTemporaryBankCard(String cardName, Long amountRestriction);

    void bankCardPasscodeLimits(Card card, String newPasscode);

    List<Card> findAllByRestrictionNumberOfDaysLeftRestriction(int numberOfDaysLeftRestriction);

    List<Card> findAllByRestrictionNumberOfDaysLeftRestrictionGreaterThan(int numberOfDaysLeftRestriction);

}