package com.joaquinrouge.appointments.appointments_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.joaquinrouge.appointments.appointments_system.model.Appointment;
import com.joaquinrouge.appointments.appointments_system.repository.IAppointmentRepository;

public class AppointmentService implements IAppointmentService{

	@Autowired
	private IAppointmentRepository repository;
	
	@Override
	public List<Appointment> getAppointments() {
		
		List<Appointment> appointmentsFromDB = repository.findAll();
		
		if(appointmentsFromDB.isEmpty()) {
			throw new IllegalArgumentException("There are no appointments in the database.");
		}
		
		return repository.findAll();
	}

	@Override
	public Appointment getAppointment(Long id) {
		Appointment appointmentFromDB = repository.findById(id).orElse(null);
		
		if(appointmentFromDB == null) {
			throw new IllegalArgumentException("Appointment with id " + id + " not found.");
		}
		
		return appointmentFromDB;
	}

	@Override
	public Appointment createAppointment(Appointment appointment) {
		return repository.save(appointment);
	}

	@Override
	public Appointment updateAppointment(Appointment appoinment) {
		Optional<Appointment> dateIsInDataBase = repository.findByDate(appoinment.getDate());
		
		if(dateIsInDataBase.isPresent()) {
			throw new IllegalArgumentException("You already have an appointment with the same date.");
		}
		
		return repository.save(appoinment);
	}

	@Override
	public Long deleteAppointment(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
