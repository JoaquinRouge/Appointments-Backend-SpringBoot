package com.joaquinrouge.appointments.appointments_system.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy-HH-mm")
	private LocalDateTime date;
	private boolean reserved;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
	private User user;
	
	public Appointment() {
		
	}

	public Appointment(Long id, LocalDateTime date, boolean reserved, User user) {
		super();
		this.id = id;
		this.date = date;
		this.reserved = reserved;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean isReserved) {
		this.reserved = isReserved;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
