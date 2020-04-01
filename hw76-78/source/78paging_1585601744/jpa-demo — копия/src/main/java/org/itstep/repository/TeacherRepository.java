package org.itstep.repository;

import org.itstep.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    @Query("SELECT t FROM Teacher t JOIN FETCH t.groups WHERE t.id = :id")
    Teacher findByIdAndFetchGroupsEagerly(@Param("id") Integer id);
}
