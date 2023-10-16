package com.tosan.card.repository;


import com.tosan.card.base.repository.BaseRepository;
import com.tosan.card.entity.BankInformation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankInformationRepository extends BaseRepository<BankInformation, Long> {

    Optional<BankInformation> findByBankNameAndAccountNumberAndNationalCodeOfTheAccountHolder(
            String bankName,int accountNumber,String nationalCodeOfTheAccountHolder);
}
