package com.tosan.card.service.Impl;


import com.tosan.card.base.service.BaseServiceImpl;
import com.tosan.card.dto.TemporaryBankCard;
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

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;


@Service
@Transactional
public class ClientServiceImpl extends BaseServiceImpl<Client, Long, ClientRepository>
        implements ClientService {

    private static final String BANK_CARD_DEFAULT_PASSCODE = "1234";

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
    public void addCreditCardWithRestriction(BankCardRequestDTO bankCardRequestDTO, Long clientId) {
        Optional<Client> client = repository.findById(clientId);
        Optional<BankAccount> bankAccount =
                client.get().getBankAccountList().stream().filter(ba ->
                        ba.getAccountName().equals(bankCardRequestDTO.getAccountName())).findFirst();
        if (bankAccount.isEmpty())
            throw new InvalidAccountException("does not exist account with this account name");
        Optional<Restriction> restriction =
                client.get().getRestrictionList().stream().filter(r ->
                        r.getName().equals(bankCardRequestDTO.getRestrictionName())).findFirst();
        if (restriction.isEmpty())
            throw new InvalidRestrictionException("does not exist restriction with this restriction name");
        if ((bankAccount.get().getBalance()) <
            (500000L + restriction.get().getAmountRestriction()))
            throw new BankAccountException("this bank account balance is insufficient");
        TemporaryBankCard temporaryBankCard =
                buildTemporaryBankCard(bankCardRequestDTO.getCardName(),
                        restriction.get().getAmountRestriction());
        Card card = cardMapper.fromTemporaryBankCardToCreditCard(temporaryBankCard);
        bankAccount.get().addCard(card);
        card.setBankAccount(bankAccount.get());
        bankAccountService.save(bankAccount.get());
        card.setRestriction(restriction.get());
        cardService.save(card);
    }

    @Override
    public void changeCardPasscode(ChangeCardPasswordDTO changeCardPasswordDTO, Long clientId) {
        Optional<Client> client = repository.findById(clientId);
        AtomicReference<Optional<Card>> cardDb = new AtomicReference<>();
        client.get().getBankAccountList().forEach(
                ba -> cardDb.set(ba.getCardList().stream().filter(
                        c -> c.getNumber().equals(changeCardPasswordDTO.getCardNumber())).findFirst()));
        Optional<Card> optionalCard = cardDb.get();
        if (optionalCard.isEmpty())
            throw new InvalidCardException("this card number does not exist!");
        Card card = optionalCard.get();
        bankCardPasscodeLimits(card, changeCardPasswordDTO.getNewPasscode());
        card.setPasscode(changeCardPasswordDTO.getNewPasscode());
        if (!card.isChangedPasscode())
            card.setBlock(false);
        card.setChangedPasscode(true);
        cardService.save(card);

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

    private TemporaryBankCard buildTemporaryBankCard(String cardName, Long amountRestriction) {
        return new TemporaryBankCard(
                cardName,
                buildCardNumber(),
                buildCardCvv2(),
                buildCardExpireDate(),
                BANK_CARD_DEFAULT_PASSCODE,
                amountRestriction
        );
    }

    private String buildCardNumber() {
        Random randomCardNumber = new Random();
        return String.valueOf(
                randomCardNumber.nextLong(4444L, 6666L)) +
               randomCardNumber.nextLong(1111L, 2222L) +
               randomCardNumber.nextLong(2222L, 3333L) +
               randomCardNumber.nextLong(3333L, 4444L);
    }

    private String buildCardCvv2() {
        Random randomCardCvv2 = new Random();
        return String.valueOf(randomCardCvv2.nextInt(111, 9999));
    }

    private LocalDate buildCardExpireDate() {
        int bankCardValidityPeriod = 40;
        return LocalDate.now().plusMonths(bankCardValidityPeriod);
    }

    private void bankCardPasscodeLimits(Card card, String newPasscode) {
        if (card.getPasscode().equals(newPasscode))
            throw new PasswordsNotSameException("duplicate new passcode!");
        if (newPasscode.equals("0000"))
            throw new PasswordsNotSameException("cannot enter 0000 as the passcode!");
    }


}
