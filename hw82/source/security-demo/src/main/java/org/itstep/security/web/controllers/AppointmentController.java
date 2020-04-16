package org.itstep.security.web.controllers;

import java.util.List;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.itstep.security.domain.entities.Appointment;
import org.itstep.security.domain.entities.AutoUser;
import org.itstep.security.domain.repositories.AppointmentRepository;
import org.itstep.security.domain.repositories.AutoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private AutoUserRepository autoUserRepository;

	@ModelAttribute
	public Appointment getAppointment(){
		return new Appointment();
	}

	@GetMapping
	public String index(){
		return "appointments";
	}

	@Transactional
	@ResponseBody
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public List<Appointment> saveAppointment(@ModelAttribute Appointment appointment){
		log.info("Save appointment {}", appointment);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		Object principal = authentication.getPrincipal();
		if(principal instanceof AutoUser) {
			AutoUser user = (AutoUser) principal;
			appointment.setUser(user);
			appointment.setStatus("Initial");
			appointmentRepository.save(appointment);
			log.debug("Saved successfully");
		} else {
			log.debug("Principal is not AutoUser");
		}
		return appointmentRepository.findAll();
	}

	@ResponseBody
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Appointment> getAppointments(){
		log.info("Get all appointments");
		return this.appointmentRepository.findAll();
	}

	@GetMapping(value = "/{appointmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAppointment(@PathVariable("appointmentId") Long appointmentId, Model model){
		Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
		log.info("Get appointment {} by id {}", appointment, appointmentId);
		model.addAttribute("appointment", appointment);
		return "appointment";
	}
	
}
