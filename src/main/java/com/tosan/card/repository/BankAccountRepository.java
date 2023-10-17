package com.tosan.card.repository;


import com.tosan.card.base.repository.BaseRepository;
import com.tosan.card.entity.BankAccount;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BankAccountRepository extends BaseRepository<BankAccount, Long> {


    boolean existsByAccountNumber(int accountNumber);

    List<BankAccount> findAllByClient_Id(Long clientId);

    Optional<BankAccount> findByAccountName(String accountName);
}
