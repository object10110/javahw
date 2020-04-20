package org.itstep.security.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath*:test-context.xml")
class CustomUserDetailsServiceTest {

    @Autowired
    UserDetailsService userDetailsService;

    @Test
    void loadUserByUsername() {
        assertNotNull(userDetailsService);
        log.info("First time");
        UserDetails user = userDetailsService.loadUserByUsername("kbowersox");
        assertEquals("kbowersox", user.getUsername());
        log.info("Second time");
        user = userDetailsService.loadUserByUsername("kbowersox");
        assertEquals("kbowersox", user.getUsername());
    }
}