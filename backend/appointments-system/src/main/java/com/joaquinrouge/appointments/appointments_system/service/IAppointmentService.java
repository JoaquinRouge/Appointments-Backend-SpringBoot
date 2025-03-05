package com.joaquinrouge.appointments.appointments_system.service;

import java.util.List;
import java.util.Optional;

import com.joaquinrouge.appointments.appointments_system.model.Appointment;

public interface IAppointmentService {
	public List<Appointment> getAppointments();
	public Optional<List<Appointment>> getAppointmentsFromUserId(Long id);
	public Appointment getAppointment(Long id);
	public Appointment createAppointment(Appointment appointment);
	public Appointment updateAppointment(Appointment appoinment);
	public Long deleteAppointment(Long id);
}
