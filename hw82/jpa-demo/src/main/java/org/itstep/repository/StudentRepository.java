package org.itstep.repository;

import org.itstep.domain.Student;
import org.itstep.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findStudentByUsername(String username);
}
