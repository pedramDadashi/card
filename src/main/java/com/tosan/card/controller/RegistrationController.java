package com.tosan.card.controller;


import com.tosan.card.dto.request.RegularClientRegistrationDTO;
import com.tosan.card.service.RegistrationService;
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
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @Operation(summary = "regular Client SingUp with DTO",
            description = "you can create a regular account for yourself for use the services in app. " +
                          "email and password have strong validity." +
                          "the input password is stored encoded from in the database." +
                          "access to this service is free.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Invalid fields in DTO")})
    @PostMapping("/signup-regular-client")
    public void regularClientSingUp(
            @Valid @RequestBody RegularClientRegistrationDTO regularClientRegistrationDTO) {
        registrationService.addRegularClient(regularClientRegistrationDTO);
    }
}

