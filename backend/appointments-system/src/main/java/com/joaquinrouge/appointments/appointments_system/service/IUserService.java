package com.joaquinrouge.appointments.appointments_system.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.joaquinrouge.appointments.appointments_system.model.User;

public interface IUserService {
	public List<User> getUsers();
	public User getUser(Long id);
	public User createUser(User User);
	public User updateUser(User user);
	public Long deleteUser(Long id);
}
