package com.tosan.card.controller;


import com.tosan.card.dto.request.*;
import com.tosan.card.dto.response.BankAccountResponseDTO;
import com.tosan.card.dto.response.RestrictionResponseDTO;
import com.tosan.card.entity.Users;
import com.tosan.card.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "change Account Password with DTO",
            description = "client must be exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Invalid new password")})
    @PutMapping("/change-account-password")
    public void changeAccountPassword(
            @Valid @RequestBody ChangeAccountPasswordDTO changeAccountPasswordDTO,
            Authentication authentication) {
        clientService.changeAccountPassword(changeAccountPasswordDTO,
                ((Users) authentication.getPrincipal()).getId());
    }

    @Operation(summary = "add Interest Free Bank Account with DTO",
            description = "client must be exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Invalid fields in DTO")})
    @PostMapping("/add-interest-free-bank-account")
    public void addInterestFreeBankAccount(
            @Valid @RequestBody BankAccountRequestDTO bankAccountDTO,
            Authentication authentication) {
        clientService.addInterestFreeBankAccount(bankAccountDTO,
                ((Users) authentication.getPrincipal()).getId());
    }

    @Operation(summary = "show Bank Account with bank account number",
            description = "desired bank account must be exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Invalid digits bank account number")})
    @GetMapping("/show-bank-account/{bankAccountNumber}")
    public BankAccountResponseDTO showBankAccount(
            @PathVariable @Valid @Min(100000000L) @Max(999999999999L) Long bankAccountNumber,
            Authentication authentication) {
        return clientService.showBankAccount(bankAccountNumber,
                ((Users) authentication.getPrincipal()).getId());
    }

    @Operation(summary = "show all Bank Account ",
            description = "at least one bank account must be exist")
    @ApiResponses(value =
    @ApiResponse(responseCode = "200", description = "Ok"))
    @GetMapping("/show-all-bank-accounts")
    public List<BankAccountResponseDTO> showAllBankAccount(Authentication authentication) {
        return clientService.showAllBankAccounts(((Users) authentication.getPrincipal()).getId());
    }

    @Operation(summary = "add Periodic Restriction with DTO",
            description = "at least one bank account must be exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Invalid fields in DTO")})
    @PostMapping("/add-periodic-restriction")
    public void addPeriodicRestriction(
            @Valid @RequestBody PeriodicRestrictionRequestDTO periodicRestrictionRequestDTO,
            Authentication authentication) {
        clientService.addPeriodicRestriction(periodicRestrictionRequestDTO,
                ((Users) authentication.getPrincipal()).getId());
    }

    @Operation(summary = "show Restriction with restriction name",
            description = "desired restriction must be exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Invalid restriction name")})
    @GetMapping("/show-restriction/{restrictionName}")
    public RestrictionResponseDTO showRestriction(
            @PathVariable @Valid @NotBlank String restrictionName,
            Authentication authentication) {
        return clientService.showRestriction(restrictionName,
                ((Users) authentication.getPrincipal()).getId());
    }

    @Operation(summary = "show all Restriction",
            description = "at least one restriction must be exist")
    @ApiResponses(value =
    @ApiResponse(responseCode = "200", description = "Ok"))
    @GetMapping("/show-all-restrictions")
    public List<RestrictionResponseDTO> showAllRestrictions(Authentication authentication) {
        return clientService.showAllRestrictions(((Users) authentication.getPrincipal()).getId());
    }

    @Operation(summary = "add Credit Card (with periodic restriction) with DTO",
            description = "at least one restriction must be exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Invalid fields in DTO")})
    @PostMapping("/add-credit-card-with-restriction")
    public void addCreditCardWithRestriction(
            @Valid @RequestBody BankCardRequestDTO bankCardRequestDTO,
            Authentication authentication) {
        clientService.addCreditCardWithRestriction(bankCardRequestDTO,
                ((Users) authentication.getPrincipal()).getId());
    }

    @Operation(summary = "change Card Passcode with DTO",
            description = "if credit card is new must be change passcode because now that credit card "+
                          "is inactive else at least one credit card must be exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400",
                    description = "Invalid digits bank credit card number OR digits new passcode")})
    @PutMapping("/change-card-passcode")
    public void changeCardPasscode(
            @Valid @RequestBody ChangeCardPasscodeDTO changeCardPasscodeDTO, Authentication authentication) {
        clientService.changeCardPasscode(changeCardPasscodeDTO,
                ((Users) authentication.getPrincipal()).getId());
    }

    @Operation(summary = "reset Card Passcode",
            description = "if the passcode of this credit card is forgotten,"+
    "it can be returned to the default passcode, considering that a new passcode has already"+
    "been chosen for this card.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Invalid digits bank credit card number")})
    @PutMapping("/reset-card-passcode/{cardNumber}")
    public void resetCardPasscode(
            @PathVariable @Valid @Pattern(regexp = "^[1-9][0-9]{15}$") String cardNumber
            , Authentication authentication) {
        clientService.resetCardPasscode(cardNumber, ((Users) authentication.getPrincipal()).getId());
    }

}

