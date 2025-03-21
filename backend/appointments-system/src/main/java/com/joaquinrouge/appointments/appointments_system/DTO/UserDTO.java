package com.joaquinrouge.appointments.appointments_system.DTO;

import com.joaquinrouge.appointments.appointments_system.model.Role;

public class UserDTO {
	private Long id;
	private String username;
	private String email;
	private Role role;
	
	public UserDTO(Long id, String username, String email,Role role) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.role = role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public Role getRole() {
		return this.role;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
