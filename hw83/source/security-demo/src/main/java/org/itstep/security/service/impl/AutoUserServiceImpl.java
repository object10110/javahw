package org.itstep.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.itstep.security.domain.entities.AutoUser;
import org.itstep.security.domain.repositories.AutoUserRepository;
import org.itstep.security.service.AutoUserService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AutoUserServiceImpl implements AutoUserService {
    final AutoUserRepository autoUserRepository;

    public AutoUserServiceImpl(AutoUserRepository autoUserRepository) {
        this.autoUserRepository = autoUserRepository;
    }

    @Override
    public AutoUser findByName(String name) {
        return autoUserRepository.findUserByUsername(name);
    }
}
