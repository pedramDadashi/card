package com.tosan.card.service;


import com.tosan.card.dto.request.RegularClientRegistrationDTO;
import org.springframework.transaction.annotation.Transactional;


public interface RegistrationService {

    void addRegularClient(RegularClientRegistrationDTO regularClientRegistrationDTO);

}
