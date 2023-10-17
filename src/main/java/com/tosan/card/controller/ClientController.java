package com.tosan.card.controller;


import com.tosan.card.dto.request.BankAccountRequestDTO;
import com.tosan.card.dto.request.ChangeAccountPasswordDTO;
import com.tosan.card.entity.Users;
import com.tosan.card.service.ClientService;
import jakarta.validation.Valid;
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


}

