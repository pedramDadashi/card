package com.tosan.card.service;



import com.tosan.card.base.service.BaseService;
import com.tosan.card.entity.BankAccount;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BankAccountService extends BaseService<BankAccount,Long> {

    @Override
    default Optional<BankAccount> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    default List<BankAccount> findAll() {
        return null;
    }

    @Override
    default boolean isExistById(Long aLong) {
        return false;
    }

    boolean existByAccountNumber(int accountNumber);

    List<BankAccount> findAllByClientId(Long clientId);

    Optional<BankAccount> findByName(String accountName);
}
