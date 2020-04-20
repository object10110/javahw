package org.itstep.security.service;

import org.itstep.security.domain.entities.AutoUser;
import org.springframework.cache.annotation.Cacheable;

public interface AutoUserService {
    AutoUser findByName(String name);
}
