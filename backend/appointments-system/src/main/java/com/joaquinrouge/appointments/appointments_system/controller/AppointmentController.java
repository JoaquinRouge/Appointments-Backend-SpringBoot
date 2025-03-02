package com.joaquinrouge.appointments.appointments_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaquinrouge.appointments.appointments_system.model.Appointment;
import com.joaquinrouge.appointments.appointments_system.service.IAppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
	
	@Autowired
	private IAppointmentService appointmentService;
	
	@GetMapping()
	public ResponseEntity<?> getAllAppointments(){
		List<Appointment> appointmentsFromDB = appointmentService.getAppointments(); 
		
		if(appointmentsFromDB.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("There are no appointments in the database.");
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(appointmentsFromDB);
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAppointment(@PathVariable Long id){
		Appointment appointmentFromDB = appointmentService.getAppointment(id);
		
		if(appointmentFromDB == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment with id " + id + " not found.");
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(appointmentFromDB);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createAppointment(@RequestBody Appointment appointment){
		try {
			Appointment create = appointmentService.createAppointment(appointment);
			return ResponseEntity.status(HttpStatus.CREATED).body(create);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateAppointment(@RequestBody Appointment appointment){
		try {
			Appointment updateAppointment = appointmentService.updateAppointment(appointment);
			return ResponseEntity.status(HttpStatus.CREATED).body(updateAppointment);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteAppointment(@PathVariable Long id){
		try {
			Long appointmentDeletedId = appointmentService.deleteAppointment(id);
			return ResponseEntity.status(HttpStatus.OK).body("Appointment with id: " + appointmentDeletedId + " was deleted.");
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); 
		}
	}
}
