package org.itstep.security.util;

import org.itstep.security.domain.entities.AutoUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Utility class for Spring Security.
 */
public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static Optional<AutoUser> getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        AutoUser user = null;
        Object principal = authentication.getPrincipal();
        if (principal instanceof AutoUser) {
            return Optional.of((AutoUser) principal);
        }
        return Optional.empty();
    }

}
