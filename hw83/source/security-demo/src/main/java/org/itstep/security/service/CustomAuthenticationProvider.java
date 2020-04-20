package org.itstep.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    final UserDetailsService customUserDetailsService;
    final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(UserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = new BCryptPasswordEncoder();// NoOpPasswordEncoder.getInstance();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String rowPassword = authentication.getCredentials().toString();
        log.info("authenticate user {} by rowPassword {}", username, rowPassword);
        UserDetails userDetails = null;
        try {
            userDetails = customUserDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(rowPassword, userDetails.getPassword())) {
                throw new BadCredentialsException("Bad Credentials");
            }
            log.info("Authenticated successfully");
        } catch (Exception ex) {
            log.error("User not found", ex);
            throw new BadCredentialsException("User not found", ex);
        }
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.equals(aClass);
    }
}
