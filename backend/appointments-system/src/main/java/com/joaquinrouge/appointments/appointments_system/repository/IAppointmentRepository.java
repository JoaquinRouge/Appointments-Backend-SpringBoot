package com.joaquinrouge.appointments.appointments_system.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaquinrouge.appointments.appointments_system.model.Appointment;

public interface IAppointmentRepository extends JpaRepository<Appointment, Long>{
	Optional<Appointment> findByDate(LocalDateTime date);
	Optional<List<Appointment>> findByUser_id(Long id);
}
