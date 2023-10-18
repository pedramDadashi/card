package com.tosan.card.service.Impl;


import com.tosan.card.base.service.BaseServiceImpl;
import com.tosan.card.dto.TemporaryBankCard;
import com.tosan.card.entity.Card;
import com.tosan.card.exception.PasswordsNotSameException;
import com.tosan.card.repository.CardRepository;
import com.tosan.card.service.CardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.tosan.card.service.Impl.ClientServiceImpl.BANK_CARD_DEFAULT_PASSCODE;

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
@Override
    public TemporaryBankCard buildTemporaryBankCard(String cardName, Long amountRestriction) {
        return new TemporaryBankCard(
                cardName,
                buildCardNumber(),
                buildCardCvv2(),
                buildCardExpireDate(),
                BANK_CARD_DEFAULT_PASSCODE,
                amountRestriction
        );
    }

    private String buildCardNumber() {
        Random randomCardNumber = new Random();
        return String.valueOf(
                randomCardNumber.nextLong(4444L, 6666L)) +
               randomCardNumber.nextLong(1111L, 2222L) +
               randomCardNumber.nextLong(2222L, 3333L) +
               randomCardNumber.nextLong(3333L, 4444L);
    }

    private String buildCardCvv2() {
        Random randomCardCvv2 = new Random();
        return String.valueOf(randomCardCvv2.nextInt(111, 9999));
    }

    private LocalDate buildCardExpireDate() {
        int bankCardValidityPeriod = 40;
        return LocalDate.now().plusMonths(bankCardValidityPeriod);
    }

    public void bankCardPasscodeLimits(Card card, String newPasscode) {
        if (card.getPasscode().equals(newPasscode))
            throw new PasswordsNotSameException("duplicate new passcode!");
        if (newPasscode.equals("0000"))
            throw new PasswordsNotSameException("cannot enter 0000 as the passcode!");
    }

    @Override
    public List<Card> findAllByRestrictionNumberOfDaysLeftRestriction(int numberOfDaysLeftRestriction) {
        return repository.findAllByRestriction_NumberOfDaysLeftRestriction(numberOfDaysLeftRestriction);
    }
    @Override
    public List<Card> findAllByRestrictionNumberOfDaysLeftRestrictionGreaterThan(int numberOfDaysLeftRestriction) {
        return repository.findAllByRestriction_NumberOfDaysLeftRestrictionGreaterThan(numberOfDaysLeftRestriction);
    }

}
