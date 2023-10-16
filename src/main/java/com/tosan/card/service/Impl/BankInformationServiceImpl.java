package com.tosan.card.service.Impl;

import com.tosan.card.base.service.BaseServiceImpl;
import com.tosan.card.dto.request.BankInformationRequestDTO;
import com.tosan.card.entity.BankInformation;
import com.tosan.card.repository.BankInformationRepository;
import com.tosan.card.service.BankInformationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankInformationServiceImpl extends BaseServiceImpl<BankInformation, Long, BankInformationRepository>
        implements BankInformationService {

    public BankInformationServiceImpl(BankInformationRepository repository) {
        super(repository);
    }

    @Override
    public Optional<BankInformation> findBankAccount(BankInformationRequestDTO bankInformationRequestDTO) {
        return repository.findByBankNameAndAccountNumberAndNationalCodeOfTheAccountHolder(
                bankInformationRequestDTO.getBankName(),
                bankInformationRequestDTO.getAccountNumber(),
                bankInformationRequestDTO.getNationalCode()
        );
    }
}
