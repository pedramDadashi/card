package com.tosan.card.controller;


import com.tosan.card.dto.request.BankAccountRequestDTO;
import com.tosan.card.dto.request.ChangeAccountPasswordDTO;
import com.tosan.card.dto.response.BankAccountResponseDTO;
import com.tosan.card.entity.Users;
import com.tosan.card.service.ClientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PutMapping("/change-account-password")
    public void changeAccountPassword(
            @Valid @RequestBody ChangeAccountPasswordDTO changeAccountPasswordDTO,
            Authentication authentication) {
        clientService.changeAccountPassword(changeAccountPasswordDTO,
                ((Users) authentication.getPrincipal()).getId());
    }

    @PostMapping("/add-interest-free-bank-account")
    public void addInterestFreeBankAccount(
            @Valid @RequestBody BankAccountRequestDTO bankAccountDTO,
            Authentication authentication) {
        clientService.addInterestFreeBankAccount(bankAccountDTO,
                ((Users) authentication.getPrincipal()).getId());
    }

    @GetMapping("/show-bank-account/{bankAccountNumber}")
    public BankAccountResponseDTO showBankAccount(
            @PathVariable @Valid @Min(100000000L) @Max(999999999999L) Long bankAccountNumber,
            Authentication authentication) {
        return clientService.showBankAccount(bankAccountNumber,
                ((Users) authentication.getPrincipal()).getId());
    }

}

