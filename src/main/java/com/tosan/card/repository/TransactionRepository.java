package com.tosan.card.repository;

import com.tosan.card.base.repository.BaseRepository;
import com.tosan.card.entity.Transaction;
import com.tosan.card.entity.enumuration.TransactionStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends BaseRepository<Transaction, Long> {

    Optional<Transaction> findByTrackingNumber(Long trackingNumber);

    List<Transaction> findByTransactionStatus(TransactionStatus transactionStatus);

    List<Transaction> findAllByCard_Number(String cardNumber);
}
