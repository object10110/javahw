package org.itstep.security.service;

import lombok.extern.slf4j.Slf4j;
import org.itstep.security.domain.entities.AutoUser;
import org.itstep.security.domain.repositories.AutoUserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomUserDetailsService implements UserDetailsService {

    final AutoUserRepository autoUserRepository;

    public CustomUserDetailsService(AutoUserRepository autoUserRepository) {
        this.autoUserRepository = autoUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Load User by username: {}", username);
        AutoUser user;
        try {
            user = autoUserRepository.findUserByUsername(username);
            log.info("Found user {}", user);
        } catch (Exception ex) {
            log.error("Not found by: {}", username);
            throw new UsernameNotFoundException("Not found by: " + username, ex);
        }
        return user;
    }
}
