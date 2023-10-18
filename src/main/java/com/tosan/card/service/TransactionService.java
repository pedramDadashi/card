package com.tosan.card.service;

import com.tosan.card.base.service.BaseService;
import com.tosan.card.dto.request.TransactionRequestDTO;
import com.tosan.card.entity.Transaction;
import com.tosan.card.entity.enumuration.TransactionStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TransactionService extends BaseService<Transaction,Long> {

    Optional<Transaction> findByTrackingNumber(Long trackingNumber);

    List<Transaction> findByTransactionStatus(TransactionStatus transactionStatus);

    List<Transaction> findAllByCardNumber(String cardNumber);

    void receiveTransaction(TransactionRequestDTO transactionRequestDTO);
}
