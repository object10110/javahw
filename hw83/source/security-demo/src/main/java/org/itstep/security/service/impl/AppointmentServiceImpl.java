package org.itstep.security.service.impl;

import org.itstep.security.domain.entities.Appointment;
import org.itstep.security.domain.entities.AutoUser;
import org.itstep.security.domain.repositories.AppointmentRepository;
import org.itstep.security.service.AppointmentService;
import org.itstep.security.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    @Transactional
    public boolean setStatus(Long appointmentId, String status) {
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
        if(appointment.isPresent()) {
            Appointment a = appointment.get();
            a.setStatus(status);
            appointmentRepository.save(a);
            return true;
        }
        return false;
    }

    @Override
    public List<Appointment> findAllByCurrentUser() {
        Optional<AutoUser> autoUserOptional = SecurityUtils.getCurrentUser();
        return appointmentRepository.findAllByUserName(autoUserOptional.map(AutoUser::getUsername).orElse(null));
    }

    @Override
    public void saveForCurrentUser(Appointment appointment) {
        Optional<AutoUser> autoUserOptional = SecurityUtils.getCurrentUser();
        if (autoUserOptional.isPresent()) {
            AutoUser user = autoUserOptional.get();
            appointment.setUser(user);
            appointment.setStatus("Initial");
            appointmentRepository.save(appointment);
        }
    }

    public Appointment findById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).orElse(null);
    }
}
