package org.itstep.security.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.itstep.security.domain.entities.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("select a from Appointment a where a.user.username=:username")
    List<Appointment> findAllByUserName(@Param("username") String username);

}
