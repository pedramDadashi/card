package com.tosan.card.service.Impl;


import com.tosan.card.base.service.BaseServiceImpl;
import com.tosan.card.dto.request.*;
import com.tosan.card.dto.response.BankAccountResponseDTO;
import com.tosan.card.dto.response.RestrictionResponseDTO;
import com.tosan.card.entity.*;
import com.tosan.card.entity.enumuration.Role;
import com.tosan.card.exception.*;
import com.tosan.card.mapper.BankMapper;
import com.tosan.card.mapper.CardMapper;
import com.tosan.card.mapper.ClientMapper;
import com.tosan.card.mapper.RestrictionMapper;
import com.tosan.card.repository.ClientRepository;
import com.tosan.card.service.*;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional
public class ClientServiceImpl extends BaseServiceImpl<Client, Long, ClientRepository>
        implements ClientService {

    private final BankAccountService bankAccountService;
    private final RestrictionService restrictionService;
    private final CardService cardService;
    private final ClientMapper clientMapper;
    private final BankMapper bankMapper;
    private final RestrictionMapper restrictionMapper;
    private final CardMapper cardMapper;
    private final PasswordEncoder passwordEncoder;

    public ClientServiceImpl(ClientRepository repository, BankAccountService bankAccountService,
                             RestrictionService restrictionService,
                             CardService cardService, ClientMapper clientMapper, BankMapper bankMapper,
                             RestrictionMapper restrictionMapper, CardMapper cardMapper,
                             PasswordEncoder passwordEncoder) {
        super(repository);
        this.bankAccountService = bankAccountService;
        this.restrictionService = restrictionService;
        this.cardService = cardService;
        this.clientMapper = clientMapper;
        this.bankMapper = bankMapper;
        this.restrictionMapper = restrictionMapper;
        this.cardMapper = cardMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addNewRegularClient(RegularClientRegistrationDTO dto) {
        if (repository.existsByEmail(dto.getEmail()))
            throw new DuplicateClientException("there is a client with this email!");
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        Client client = clientMapper.fromRegularClientRegistrationDTOToNewRegularClient(dto);
        client.setRole(Role.CLIENT);
        client.setIsActive(true);
        repository.save(client);
    }


    @Override
    public Optional<Client> findByUsername(String email) {
        return repository.findByEmail(email);
    }


    @Override
    public void changeAccountPassword(ChangeAccountPasswordDTO changeAccountPasswordDTO, Long clientId) {
        if (!changeAccountPasswordDTO.getNewPassword().
                equals(changeAccountPasswordDTO.getConfirmNewPassword()))
            throw new PasswordsNotSameException("confirm password are not the same as new password!");
        Optional<Client> client = repository.findById(clientId);
        client.get().setPassword(passwordEncoder.encode(changeAccountPasswordDTO.getConfirmNewPassword()));
    }

    @Override
    public void addInterestFreeBankAccount(BankAccountRequestDTO bankAccountDTO, Long clientId) {
        Optional<Client> client = repository.findById(clientId);
        if (!client.get().getBankAccountList().isEmpty()) {
            if (client.get().getBankAccountList().stream().anyMatch(ba ->
                    Objects.equals(ba.getAccountNumber(), bankAccountDTO.getAccountNumber())))
                throw new BankAccountException("this account number already exist!");
        }
        BankAccount bankAccount = bankMapper.fromBankAccountRequestDTOInterestFreeAccount(bankAccountDTO);
        client.get().addBankAccount(bankAccount);
        bankAccount.setClient(client.get());
        bankAccountService.save(bankAccount);
        repository.save(client.get());
    }


//    @Override
//    @Transactional(readOnly = true)
//    public List<BankAccountResponseDTO> showAllBankAccounts(Long clintId) {
//        List<BankAccountResponseDTO> baDTOS = new ArrayList<>();
//        List<BankAccount> bankAccounts = bankAccountService.findAllByClientId(clintId);
//        if (bankAccounts.isEmpty())
//            return baDTOS;
//        bankAccounts.forEach(ba -> baDTOS.add(bankMapper.convertToBankAccountDTO(ba)));
//        return baDTOS;
//    }

//    @Override
//    @Transactional(readOnly = true)
//    public BankAccountResponseDTO showBankAccount(Long bankAccountId, Long clientId) {
//        BankAccountResponseDTO bankAccountResponseDTO = new BankAccountResponseDTO();
//        Optional<Client> client = repository.findById(clientId);
//        Optional<BankAccount> bankAccount = client.get().getBankAccountList()
//                .stream().filter(ba -> Objects.equals(ba.getId(), bankAccountId)).findFirst();
//        if (bankAccount.isEmpty())
//            return bankAccountResponseDTO;
//        return bankMapper.convertToBankAccountDTO(bankAccount.get());
////        if (client.get().getBankAccountList()
////                .stream().noneMatch(ba -> ba.getId() == bankAccountId))
////            return bankAccountResponseDTO;
//
//    }

    @Override
    public void addNewPeriodicRestriction(RestrictionRequestDTO restrictionRequestDTO, Long clientId) {

    }


//    @Override
//    public void addNewNormalRestriction(RestrictionRequestDTO restrictionRequestDTO,
//                                        Long clientId) {
//
//        NormalRestriction normalRestriction = restrictionMapper,.(restrictionRequestDTO);
//        restrictionService.save(normalRestriction);
//        return new ProjectResponse("200", "ADD NORMAL RESTRICTION SUCCESSFULLY");
//    }

    @Override
    @Transactional(readOnly = true)
    public List<RestrictionResponseDTO> findAllRestrictions(Long clientId) {
        List<RestrictionResponseDTO> restrictionResponseDTOList = new ArrayList<>();
        Optional<Client> client = repository.findById(clientId);
        if (!client.get().getRestrictionList().isEmpty())
            client.get().getRestrictionList().stream().forEach(restriction ->
                    restrictionResponseDTOList.add(
                            restrictionMapper.fromRestrictionToRestrictionDTO(restriction)
                    )
            );
        return restrictionResponseDTOList;
    }

    @Override
    @Transactional(readOnly = true)
    public RestrictionResponseDTO showRestriction(String restrictionName, Long clientId) {
        Optional<Client> client = repository.findById(clientId);
        if (client.get().getRestrictionList().isEmpty()) {
            return new RestrictionResponseDTO();
        }
        Optional<Restriction> restriction = client.get().getRestrictionList()
                .stream().filter(r ->
                        r.getName().equals(restrictionName)).findFirst();
        if (restriction.isPresent())
            return restrictionMapper.fromRestrictionToRestrictionDTO(restriction.get());
        return new RestrictionResponseDTO();
    }

    @Override
    public void addNewBankCardWithRestriction(BankCardRequestDTO bankCardRequestDTO,
                                              Long clientId) {
        Optional<Client> client = repository.findById(clientId);
        if (client.get().getBankAccountList().stream().anyMatch(
                ba ->
                        ba.getCardList().stream().anyMatch(
                                c ->
                                        c.getName().equals(bankCardRequestDTO.getCardName())
                        )
        )
        ) throw new InvalidCardException("this card does exist");
        Optional<BankAccount> bankAccount =
                client.get().getBankAccountList().stream().filter(ba ->
                        ba.getAccountName().equals(bankCardRequestDTO.getAccountName())).findFirst();
        if (bankAccount.isEmpty())
            throw new BankAccountException("this account does not exist!");
        Optional<Restriction> restriction =
                client.get().getRestrictionList().stream().filter(r ->
                        r.getName().equals(bankCardRequestDTO.getRestrictionName())).findFirst();
        if (restriction.isEmpty())
            throw new RestrictionDoesNotExistException("this restriction does not exist!");
//        if ((bankAccount.get() .getBankInformation().getBalance()) <
//            (500000L + restriction.get().getAmountRestriction()))
//            throw new BankAccountException("this bank account balance is insufficient");
//        Card card = cardMapper.convertToCreditCard(
//                bankCardRequestDTO.getCardName(), bankAccount.get(), restriction.get());
//        cardService.save(card);
    }

    @Override
    public void changeCardPassword(ChangeCardPasswordDTO changeCardPasswordDTO,
                                   Long clientId) {

        Optional<Card> card = cardService.findByNumber(changeCardPasswordDTO.getCardNumber());
        if (card.isEmpty())
            card = cardService.findByName(changeCardPasswordDTO.getCardName());
        if (card.isEmpty())
            throw new InvalidCardException("this card does not exist!");

        Optional<Client> client = repository.findById(clientId);

    }


}
