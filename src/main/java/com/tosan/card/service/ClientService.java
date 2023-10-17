package com.tosan.card.service;



import com.tosan.card.dto.request.*;
import com.tosan.card.dto.response.BankAccountResponseDTO;
import com.tosan.card.dto.response.RestrictionResponseDTO;
import com.tosan.card.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService extends UsersService<Client> {

    @Override
    void save(Client client);

    @Override
    void delete(Client client);

    @Override
    Optional<Client> findById(Long aLong);

    @Override
    List<Client> findAll();

    @Override
    Optional<Client> findByUsername(String email);

    void addNewRegularClient(RegularClientRegistrationDTO regularClientRegistrationDTO);

    void changeAccountPassword(ChangeAccountPasswordDTO changeAccountPasswordDTO, Long clientId);

    void addInterestFreeBankAccount(BankAccountRequestDTO bankAccountDTO, Long clientId);

    BankAccountResponseDTO showBankAccount(Long bankAccountNumber, Long clientId);

    List<BankAccountResponseDTO> showAllBankAccounts(Long clientId);

    void addPeriodicRestriction(PeriodicRestrictionRequestDTO periodicRestrictionRequestDTO, Long clientId);

//    List<BankAccountResponseDTO> showAllBankAccounts(Long clintId);

//    BankAccountResponseDTO showBankAccount(Long bankAccountId, Long clientId);

//    void addNewNormalRestriction(RestrictionRequestDTO restrictionRequestDTO, Long clientId);

//    void addNewPeriodicRestriction(RestrictionRequestDTO restrictionRequestDTO, Long clientId);

    void addNewBankCardWithRestriction(BankCardRequestDTO bankCardRequestDTO, Long clientId);

    List<RestrictionResponseDTO> findAllRestrictions(Long clientId);

    RestrictionResponseDTO showRestriction(String restrictionName, Long clientId);

    void changeCardPassword(ChangeCardPasswordDTO changeCardPasswordDTO, Long clientId);



}
