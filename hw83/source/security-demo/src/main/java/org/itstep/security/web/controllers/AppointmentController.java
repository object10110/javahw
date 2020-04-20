package org.itstep.security.web.controllers;

import lombok.extern.slf4j.Slf4j;
import org.itstep.security.domain.entities.Appointment;
import org.itstep.security.domain.entities.AutoUser;
import org.itstep.security.service.AppointmentService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @ModelAttribute
    public Appointment getAppointment() {
        return new Appointment();
    }

    @GetMapping
    public String index() {
        return "appointments";
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public List<Appointment> saveAppointment(@ModelAttribute Appointment appointment) {
        log.info("Save appointment {}", appointment);
        appointmentService.saveForCurrentUser(appointment);
        return appointmentService.findAllByCurrentUser();
    }

    @ResponseBody
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Appointment> getAppointments() {
        log.info("Get all appointments");
        return appointmentService.findAllByCurrentUser();
    }

    @GetMapping(value = "/{appointmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAppointment(@PathVariable("appointmentId") Long appointmentId, Model model) {
        Appointment appointment = appointmentService.findById(appointmentId);
        log.info("Get appointment {} by id {}", appointment, appointmentId);
        model.addAttribute("appointment", appointment);
        return "appointment";
    }

    @ResponseBody
    @GetMapping("/{appointmentId}/confirm")
    public String confirm(@PathVariable("appointmentId") Long appointmentId) {
        appointmentService.setStatus(appointmentId, "Confirmed");
        return "Confirmed";
    }

    @ResponseBody
    @GetMapping("/{appointmentId}/cancel")
    public String cancel(@PathVariable("appointmentId") Long appointmentId) {
       appointmentService.setStatus(appointmentId, "Cancelled");
        return "Cancelled";
    }

    @ResponseBody
    @GetMapping("/{appointmentId}/complete")
    public String complete(@PathVariable("appointmentId") Long appointmentId) {
        appointmentService.setStatus(appointmentId, "Completed");
        return "Completed";
    }
}
