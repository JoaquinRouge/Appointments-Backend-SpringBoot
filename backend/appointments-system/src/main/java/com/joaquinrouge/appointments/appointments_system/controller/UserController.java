package com.joaquinrouge.appointments.appointments_system.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaquinrouge.appointments.appointments_system.model.User;
import com.joaquinrouge.appointments.appointments_system.service.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@GetMapping
	public ResponseEntity<?> getAllUsers(){
		List<User> usersFromDB = userService.getUsers();
		
		if(usersFromDB.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("There are no users in the database.");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(usersFromDB);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable Long id){
		User userFromDB = userService.getUser(id);
		
		if(userFromDB == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(userFromDB);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody User user){
		
		try {
			User create = userService.createUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(create);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody User user){
		try {
			User updateUser = userService.updateUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(updateUser);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id){
		try {
			Long deleteUser = userService.deleteUser(id);
			return ResponseEntity.status(HttpStatus.OK).body("User with id: " + deleteUser + " was deleted.");
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); 
		}
		
	}
	
}
