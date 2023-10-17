package com.tosan.card.controller;


import com.tosan.card.dto.request.*;
import com.tosan.card.dto.response.BankAccountResponseDTO;
import com.tosan.card.dto.response.RestrictionResponseDTO;
import com.tosan.card.entity.Users;
import com.tosan.card.service.ClientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/show-all-bank-accounts")
    public List<BankAccountResponseDTO> showAllBankAccount(Authentication authentication) {
        return clientService.showAllBankAccounts(((Users) authentication.getPrincipal()).getId());
    }

    @PostMapping("/add-periodic-restriction")
    public void addPeriodicRestriction(
            @Valid @RequestBody PeriodicRestrictionRequestDTO periodicRestrictionRequestDTO,
            Authentication authentication) {
        clientService.addPeriodicRestriction(periodicRestrictionRequestDTO,
                ((Users) authentication.getPrincipal()).getId());
    }

    @GetMapping("/show-restriction/{restrictionName}")
    public RestrictionResponseDTO showRestriction(
            @PathVariable @Valid @NotBlank String restrictionName,
            Authentication authentication) {
        return clientService.showRestriction(restrictionName,
                ((Users) authentication.getPrincipal()).getId());
    }

    @GetMapping("/show-all-restrictions")
    public List<RestrictionResponseDTO> showAllRestrictions(Authentication authentication) {
        return clientService.showAllRestrictions(((Users) authentication.getPrincipal()).getId());
    }

    @PostMapping("/add-credit-card-with-restriction")
    public void addCreditCardWithRestriction(
            @Valid @RequestBody BankCardRequestDTO bankCardRequestDTO,
            Authentication authentication) {
        clientService.addCreditCardWithRestriction(bankCardRequestDTO,
                ((Users) authentication.getPrincipal()).getId());
    }

    @PutMapping("/change-card-passcode")
    public void changeCardPasscode(
            @Valid @RequestBody ChangeCardPasswordDTO changeCardPasswordDTO, Authentication authentication) {
        clientService.changeCardPasscode(changeCardPasswordDTO,
                ((Users) authentication.getPrincipal()).getId());
    }

    @PutMapping("/reset-card-passcode/{cardNumber}")
    public void resetCardPasscode(
            @PathVariable @Valid @Pattern(regexp = "^[1-9][0-9]{15}$") String cardNumber
            , Authentication authentication) {
        clientService.resetCardPasscode(cardNumber, ((Users) authentication.getPrincipal()).getId());
    }

}

