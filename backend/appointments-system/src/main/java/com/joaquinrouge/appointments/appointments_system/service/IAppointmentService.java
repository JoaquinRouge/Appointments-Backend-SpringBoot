package com.joaquinrouge.appointments.appointments_system.service;

import java.util.List;

import com.joaquinrouge.appointments.appointments_system.model.Appointment;

public interface IAppointmentService {
	public List<Appointment> getAppointments();
	public Appointment getAppointment(Long id);
	public Appointment createAppointment(Appointment appointment);
	public Appointment updateAppointment(Appointment appoinment);
	public Long deleteAppointment(Long id);
}
