package org.itstep.security.domain.repositories;

import org.itstep.security.domain.entities.AutoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutoUserRepository extends JpaRepository<AutoUser, Long> {

    AutoUser findUserByUsername(String username);

}
