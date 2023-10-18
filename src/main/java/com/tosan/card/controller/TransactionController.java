package com.tosan.card.controller;


import com.tosan.card.dto.request.TransactionRequestDTO;
import com.tosan.card.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/receive-bank-transaction")
    public void receiveBankTransaction(
            @Valid @RequestBody TransactionRequestDTO transactionRequestDTO) {
        transactionService.receiveTransaction(transactionRequestDTO);
    }
}

