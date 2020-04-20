package org.itstep.security.service;

import org.itstep.security.domain.entities.Appointment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AppointmentService {

    boolean setStatus(Long appointmentId, String status);

    @Transactional(readOnly = true)
    List<Appointment> findAllByCurrentUser();

    void saveForCurrentUser(Appointment appointment);

    @Transactional(readOnly = true)
    public Appointment findById(Long appointmentId);
}
