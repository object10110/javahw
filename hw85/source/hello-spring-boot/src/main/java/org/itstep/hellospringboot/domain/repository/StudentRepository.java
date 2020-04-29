package org.itstep.hellospringboot.domain.repository;

import org.itstep.hellospringboot.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasAnyRole('ADMIN')")
@RepositoryRestResource
public interface StudentRepository extends JpaRepository<Student, Long> {
}
