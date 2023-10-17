package com.tosan.card.controller;

import com.tosan.card.dto.request.ClientRegistrationDTO;
import com.tosan.card.dto.request.RegularClientRegistrationDTO;
import com.tosan.card.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/signup-regular-client")
    public void regularClientSingUp(
            @Valid @RequestBody RegularClientRegistrationDTO regularClientRegistrationDTO) {
        registrationService.addRegularClient(regularClientRegistrationDTO);
    }
}

