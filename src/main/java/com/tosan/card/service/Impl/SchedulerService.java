package com.tosan.card.service.Impl;


import com.tosan.card.entity.BankAccount;
import com.tosan.card.entity.Card;
import com.tosan.card.entity.Restriction;
import com.tosan.card.service.BankAccountService;
import com.tosan.card.service.CardService;
import com.tosan.card.service.RestrictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SchedulerService {

    private final CardService cardService;
    private final RestrictionService restrictionService;
    private final BankAccountService bankAccountService;

    @Scheduled(cron = "0 0 0 * * ?")
    private void updateRestriction() {
        List<Card> expiredCardList =
                cardService.findAllByRestrictionNumberOfDaysLeftRestriction(1);
        if (!expiredCardList.isEmpty())
            checkExpired(expiredCardList);
        List<Card> activateCardList =
                cardService.findAllByRestrictionNumberOfDaysLeftRestrictionGreaterThan(1);
        if (!activateCardList.isEmpty())
            checkUpdateRestrictionAmount(activateCardList);

    }

    private void checkExpired(List<Card> expiredCardList) {
        for (Card card : expiredCardList) {
            BankAccount bankAccount = card.getBankAccount();
            Restriction restriction = card.getRestriction();
            card.setBlock(true);
            bankAccount.setBalance(
                    bankAccount.getBalance() + card.getCardBalance()
            );
            card.setCardBalance(0L);
            restriction.setRemainingAmountFromRestriction(0L);
            restriction.setNumberOfDaysLeftRestriction(0);
            bankAccountService.save(bankAccount);
            restrictionService.save(restriction);
            cardService.save(card);
        }
    }

    private void checkUpdateRestrictionAmount(List<Card> activateCardList) {
        for (Card card : activateCardList) {
            BankAccount bankAccount = card.getBankAccount();
            Restriction restriction = card.getRestriction();
            int numberOfDaysLeftRestriction = restriction.getNumberOfDaysLeftRestriction();
            Long amountRestriction = restriction.getAmountRestriction();
            Long cardBalance = card.getCardBalance();

            if (restriction.updateRestriction()) {
                bankAccount.deductionBalanceAccount(cardBalance);
                card.setCardBalance(amountRestriction);
                restriction.setRemainingAmountFromRestriction(amountRestriction);
            }
            restriction.setNumberOfDaysLeftRestriction(numberOfDaysLeftRestriction - 1);
            bankAccountService.save(bankAccount);
            restrictionService.save(restriction);
            cardService.save(card);
        }
    }

}
