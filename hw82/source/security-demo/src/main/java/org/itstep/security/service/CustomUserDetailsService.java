package org.itstep.security.service;

import org.itstep.security.domain.entities.AutoUser;
import org.itstep.security.domain.repositories.AutoUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    final AutoUserRepository autoUserRepository;

    public CustomUserDetailsService(AutoUserRepository autoUserRepository) {
        this.autoUserRepository = autoUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        AutoUser autoUser = autoUserRepository.findUserByUsername(username);
//        return new User(autoUser.getUsername(), autoUser.getPassword(),
//                AuthorityUtils.createAuthorityList(autoUser.getRole()));
        AutoUser user;
        try {
            user = autoUserRepository.findUserByUsername(username);
        } catch (Exception ex) {
            throw new UsernameNotFoundException("Not found by: " + username, ex);
        }
        return user;
    }
}
