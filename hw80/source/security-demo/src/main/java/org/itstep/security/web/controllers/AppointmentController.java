package org.itstep.security.web.controllers;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.itstep.security.domain.entities.Appointment;
import org.itstep.security.domain.entities.AutoUser;
import org.itstep.security.domain.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@ModelAttribute
	public Appointment getAppointment(){
		return new Appointment();
	}

	@GetMapping
	public String index(){
		return "appointments";
	}

	@ResponseBody
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public List<Appointment> saveAppointment(@ModelAttribute Appointment appointment){
		log.info("Save appointment {}", appointment);
		AutoUser user = new AutoUser();
		user.setEmail("test@email.com");
		user.setFirstName("Joe");
		user.setLastName("Doe");
		appointment.setUser(user);
		appointment.setStatus("Initial");
		appointmentRepository.save(appointment);
		return this.appointmentRepository.findAll();
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
