package com.tosan.card.service.Impl;

import com.tosan.card.dto.request.RegularClientRegistrationDTO;
import com.tosan.card.service.ClientService;
import com.tosan.card.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    @Qualifier(value = "clientServiceImpl")
    private final ClientService clientService;

    @Override
    public void addRegularClient(RegularClientRegistrationDTO dto) {
         clientService.addNewRegularClient(dto);
    }



}
