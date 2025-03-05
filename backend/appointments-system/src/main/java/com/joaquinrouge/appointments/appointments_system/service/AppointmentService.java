package com.joaquinrouge.appointments.appointments_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaquinrouge.appointments.appointments_system.model.Appointment;
import com.joaquinrouge.appointments.appointments_system.repository.IAppointmentRepository;

@Service
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
		
		if(repository.findByDate(appointment.getDate()).isPresent()) {
			throw new IllegalArgumentException("There is already an appointment in this date.");
		}
		
		return repository.save(appointment);
	}

	@Override
	public Appointment updateAppointment(Appointment appoinment) {

		return repository.save(appoinment);
	}

	@Override
	public Long deleteAppointment(Long id) {
		
	    if (!repository.existsById(id)) {
	        throw new IllegalArgumentException("Appointment with id " + id + " not found.");
	    }
		
		repository.deleteById(id);
		
		return id;
	}

	@Override
	public Optional<List<Appointment>> getAppointmentsFromUserId(Long id) {
		Optional<List<Appointment>> listFromDB = repository.findByUser_id(id);
		
		if(listFromDB.isEmpty()) {
			throw new IllegalArgumentException("The user with id " + id + " does'nt have any appointment.");
		}
		
		return listFromDB;
	}

}
