package com.tosan.card.repository;



import com.tosan.card.base.repository.BaseRepository;
import com.tosan.card.entity.Users;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository<U extends Users> extends BaseRepository<U, Long> {

    Optional<U> findByEmail(String email);


}
