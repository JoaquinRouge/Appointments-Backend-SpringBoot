package com.joaquinrouge.appointments.appointments_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaquinrouge.appointments.appointments_system.model.User;
import com.joaquinrouge.appointments.appointments_system.repository.IUserRepository;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository repository;

    @Override
    public List<User> getUsers() {
        return repository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return repository.findById(id).orElse(null);
    }


    @Override
    public User createUser(User user) {
    	
    	if(repository.existsByEmail(user.getEmail())) {
    		throw new IllegalArgumentException("The email already exists.");
    	}
    	
    	if(repository.existsByUsername(user.getUsername())) {
    		throw new IllegalArgumentException("The username already exists.");
    	}
    	
        return repository.save(user);
    }

    @Override
    public User updateUser(User user) {
    	
    	User userFromDB = repository.findById(user.getId()).orElse(null);
    	
        if (userFromDB == null) {
        	throw new IllegalArgumentException("The user was not found.");
        }
        
    	if(repository.existsByEmailAndIdNot(user.getEmail(), user.getId())) {
    		throw new IllegalArgumentException("The email already exists.");
    	}
    	
    	
    	if(repository.existsByUsernameAndIdNot(user.getUsername(), user.getId())) {
    		throw new IllegalArgumentException("The username already exists.");
    	}
        
    	userFromDB.setEmail(user.getEmail());
    	userFromDB.setUsername(user.getUsername());
    	
        return repository.save(userFromDB);
    }

    @Override
    public Long deleteUser(Long id) {
        if (!repository.existsById(id)) {
        	throw new IllegalArgumentException("User with id " + id + " not found.");
        }
        repository.deleteById(id);
        return id;
    }
    
}

