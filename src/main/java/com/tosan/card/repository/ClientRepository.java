package com.tosan.card.repository;



import com.tosan.card.entity.Client;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends UsersRepository<Client> {

    @Override
    Optional<Client> findByEmail(String email);

}
