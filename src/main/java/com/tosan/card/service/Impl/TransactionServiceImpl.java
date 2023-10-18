package com.tosan.card.service.Impl;


import com.tosan.card.base.service.BaseServiceImpl;
import com.tosan.card.dto.request.TransactionRequestDTO;
import com.tosan.card.entity.Card;
import com.tosan.card.entity.Transaction;
import com.tosan.card.entity.enumuration.TransactionStatus;
import com.tosan.card.exception.CustomTimeoutException;
import com.tosan.card.exception.InvalidAmountException;
import com.tosan.card.exception.InvalidCardException;
import com.tosan.card.mapper.TransactionMapper;
import com.tosan.card.repository.TransactionRepository;
import com.tosan.card.service.CardService;
import com.tosan.card.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class TransactionServiceImpl extends BaseServiceImpl<Transaction, Long, TransactionRepository>
        implements TransactionService {

    private final CardService cardService;
    private final TransactionMapper transactionMapper;


    protected TransactionServiceImpl(TransactionRepository repository, CardService cardService,
                                     TransactionMapper transactionMapper) {
        super(repository);
        this.cardService = cardService;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public Optional<Transaction> findByTrackingNumber(Long trackingNumber) {
        return repository.findByTrackingNumber(trackingNumber);
    }

    @Override
    public List<Transaction> findByTransactionStatus(TransactionStatus transactionStatus) {
        return repository.findByTransactionStatus(transactionStatus);
    }

    @Override
    public List<Transaction> findAllByCardNumber(String cardNumber) {
        return repository.findAllByCard_Number(cardNumber);
    }

    @Override
    public void receiveBankTransaction(TransactionRequestDTO transactionRequestDTO) {
        if (transactionRequestDTO.getTimeTransaction().isBefore(LocalDateTime.now()))
            throw new CustomTimeoutException("transaction has timed out");
        Optional<Card> cardDb = cardService.findByNumber(transactionRequestDTO.getCardNumber());
        if (cardDb.isEmpty())
            throw new InvalidCardException("card does not exist with cardDb number");
        Card card = cardDb.get();
        Transaction transaction =
                transactionMapper.fromTransactionRequestDTOToTemporaryTransaction(transactionRequestDTO);
        if (card.getRestriction().getRemainingAmountFromRestriction() <
            (transactionRequestDTO.getAmountTransaction()))
            throw new InvalidAmountException("transaction amount exceeds the card restriction");
        if (card.getCardBalance() <=
            (500000L + transactionRequestDTO.getAmountTransaction()))
            throw new InvalidAmountException("transaction amount exceeds the card balance");
        card.getRestriction().deductionFromRemainingAmount(transactionRequestDTO.getAmountTransaction());
        card.deductionFromCardBalance(transactionRequestDTO.getAmountTransaction());
        cardService.save(card);
        repository.save(buildAcceptTransaction(card, transaction));
    }

    private Transaction buildAcceptTransaction(Card card, Transaction transaction) {
        transaction.setTransactionStatus(TransactionStatus.ACCEPTED);
        transaction.setCard(card);
        transaction.setBalance(card.getCardBalance());
        transaction.setTrackingNumber(buildTrackingNumber());
        return transaction;
    }


    private Transaction buildRejectTransaction(Card card, Transaction transaction) {
        transaction.setTransactionStatus(TransactionStatus.REJECTED);
        transaction.setCard(card);
        transaction.setBalance(card.getCardBalance());
        transaction.setTrackingNumber(-1L);
        return transaction;
    }

    private Long buildTrackingNumber() {
        Random randomCardNumber = new Random();
        return randomCardNumber.nextLong(1111L, 9999999L);

    }
}
