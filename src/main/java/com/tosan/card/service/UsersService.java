package com.tosan.card.service;


import com.tosan.card.base.service.BaseService;
import com.tosan.card.entity.Users;

import java.util.Optional;

public interface UsersService<E extends Users> extends BaseService<E,Long> {

    Optional<E> findByUsername(String email);

}
