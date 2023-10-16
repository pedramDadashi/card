package com.tosan.card.service;



import com.tosan.card.base.service.BaseService;
import com.tosan.card.dto.request.BankInformationRequestDTO;
import com.tosan.card.entity.BankInformation;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface BankInformationService extends BaseService<BankInformation,Long> {


    Optional<BankInformation> findBankAccount(BankInformationRequestDTO bankInformationRequestDTO);
}
