package com.tosan.card.service.Impl;


import com.tosan.card.base.service.BaseServiceImpl;
import com.tosan.card.dto.request.*;
import com.tosan.card.dto.response.BankAccountResponseDTO;
import com.tosan.card.dto.response.RestrictionResponseDTO;
import com.tosan.card.entity.BankAccount;
import com.tosan.card.entity.Card;
import com.tosan.card.entity.Client;
import com.tosan.card.entity.Restriction;
import com.tosan.card.entity.enumuration.Period;
import com.tosan.card.entity.enumuration.Role;
import com.tosan.card.exception.*;
import com.tosan.card.mapper.BankMapper;
import com.tosan.card.mapper.CardMapper;
import com.tosan.card.mapper.ClientMapper;
import com.tosan.card.mapper.RestrictionMapper;
import com.tosan.card.repository.ClientRepository;
import com.tosan.card.service.BankAccountService;
import com.tosan.card.service.CardService;
import com.tosan.card.service.ClientService;
import com.tosan.card.service.RestrictionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


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
        BankAccount bankAccount = bankMapper.fromBankAccountRequestDTOToInterestFreeAccount(bankAccountDTO);
        client.get().addBankAccount(bankAccount);
        bankAccount.setClient(client.get());
        bankAccountService.save(bankAccount);
        repository.save(client.get());
    }

    @Override
    @Transactional(readOnly = true)
    public BankAccountResponseDTO showBankAccount(Long bankAccountNumber, Long clientId) {
        Optional<Client> client = repository.findById(clientId);
        if (client.get().getBankAccountList().isEmpty())
            throw new InvalidAccountException("does not exist account with this account number");
        Optional<BankAccount> bankAccount = client.get().getBankAccountList().stream().filter(ba ->
                Objects.equals(ba.getAccountNumber(), bankAccountNumber)).findFirst();
        if (bankAccount.isEmpty())
            throw new InvalidAccountException("does not exist account with this account number");
        return bankMapper.fromBankAccountToBankAccountResponseDTO(bankAccount.get());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BankAccountResponseDTO> showAllBankAccounts(Long clientId) {
        Optional<Client> client = repository.findById(clientId);
        if (client.get().getBankAccountList().isEmpty())
            throw new InvalidAccountException("does not exist account");
        List<BankAccountResponseDTO> bankAccountResponseDTOList = new ArrayList<>();
        client.get().getBankAccountList().forEach(ba ->
                bankAccountResponseDTOList.add(
                        bankMapper.fromBankAccountToBankAccountResponseDTO(ba)));
        return bankAccountResponseDTOList;
    }

    @Override
    public void addPeriodicRestriction(PeriodicRestrictionRequestDTO periodicRestrictionRequestDTO
            , Long clientId) {
        Optional<Client> client = repository.findById(clientId);
        if (!client.get().getRestrictionList().isEmpty()) {
            if (client.get().getRestrictionList().stream().anyMatch(r ->
                    Objects.equals(r.getName(),
                            periodicRestrictionRequestDTO.getName())))
                throw new RestrictionException("this restriction name already exist!");
        }
        Restriction restriction = restrictionMapper.
                fromPeriodicRestrictionRequestDTOToPeriodicRestriction(periodicRestrictionRequestDTO);
        setPeriodDaysFromPeriodType(restriction);
        client.get().addRestriction(restriction);
        restriction.setClient(client.get());
        restrictionService.save(restriction);
        repository.save(client.get());
    }

    @Override
    @Transactional(readOnly = true)
    public RestrictionResponseDTO showRestriction(String restrictionName, Long clientId) {
        Optional<Client> client = repository.findById(clientId);
        if (client.get().getRestrictionList().isEmpty())
            throw new InvalidRestrictionException("does not exist restriction with this restriction name");
        Optional<Restriction> restriction = client.get().getRestrictionList().stream().filter(r ->
                Objects.equals(r.getName(), restrictionName)).findFirst();
        if (restriction.isEmpty())
            throw new InvalidRestrictionException("does not exist restriction with this restriction name");
        return restrictionMapper.fromRestrictionToRestrictionResponseDTO(restriction.get());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RestrictionResponseDTO> showAllRestrictions(Long clientId) {
        Optional<Client> client = repository.findById(clientId);
        if (client.get().getRestrictionList().isEmpty())
            throw new InvalidRestrictionException("does not exist restriction");
        List<RestrictionResponseDTO> restrictionResponseDTOList = new ArrayList<>();
        client.get().getRestrictionList().stream().forEach(r ->
                restrictionResponseDTOList.add(
                        restrictionMapper.fromRestrictionToRestrictionResponseDTO(r)));
        return restrictionResponseDTOList;
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


    private void setPeriodDaysFromPeriodType(Restriction restriction) {
        if (!restriction.getPeriod().name().equals(Period.CUSTOM.name())) {
            if (restriction.getPeriod().name().equals(Period.DAILY.name())) {
                restriction.setPeriodDays(1);
                return;
            }
            if (restriction.getPeriod().name().equals(Period.WEEKLY.name())) {
                restriction.setPeriodDays(7);
                return;
            }
            if (restriction.getPeriod().name().equals(Period.MONTHLY.name())) {
                restriction.setPeriodDays(30);
            }
        }
    }

}
