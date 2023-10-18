package com.tosan.card.controller;


import com.tosan.card.dto.request.TransactionRequestDTO;
import com.tosan.card.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "receive Bank Transaction with DTO",
            description = "this free access portal exists to receive bank transactions made with " +
                          "credit cards issued by the app itself, " +
                          "if the transaction is successful, it is stored in the database" +
                          "for transaction history.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Invalid fields in DTO")})
    @PostMapping("/receive-bank-transaction")
    public void receiveBankTransaction(
            @Valid @RequestBody TransactionRequestDTO transactionRequestDTO) {
        transactionService.receiveTransaction(transactionRequestDTO);
    }
}

