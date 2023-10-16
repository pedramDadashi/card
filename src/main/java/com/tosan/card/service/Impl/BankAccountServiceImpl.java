package com.tosan.card.service.Impl;


import com.tosan.card.base.service.BaseServiceImpl;
import com.tosan.card.entity.BankAccount;
import com.tosan.card.repository.BankAccountRepository;
import com.tosan.card.service.BankAccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BankAccountServiceImpl extends BaseServiceImpl<BankAccount, Long, BankAccountRepository>
        implements BankAccountService {

    public BankAccountServiceImpl(BankAccountRepository repository) {
        super(repository);
    }

    @Override
    public boolean existByAccountNumber(int accountNumber) {
        return repository.existsByAccountNumber(accountNumber);
    }

    @Override
    public List<BankAccount> findAllByClientId(Long clientId) {
        return repository.findAllByClient_Id(clientId);
    }

    @Override
    public Optional<BankAccount> findByName(String accountName) {
        return repository.findByName(accountName);
    }
}
