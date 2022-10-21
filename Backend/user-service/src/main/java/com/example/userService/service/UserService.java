package com.example.userService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userService.exception.NoUserExistsException;
import com.example.userService.exception.UserAlreadyExistsException;
import com.example.userService.model.User;
import com.example.userService.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

//	Create
	public User create(User u) throws UserAlreadyExistsException{
		if(userRepository.existsById(u.getEmail())) {
			throw new UserAlreadyExistsException();
		}
		User savedUser= userRepository.save(u);
		return savedUser;
	}
	
//	Retrieve
	public List<User> getAll(){
		return userRepository.findAll();
	}
	
//	Retrieve by Email
	public User getByEmail(String email)throws NoUserExistsException {
		if(!userRepository.existsById(email)) {
			throw new NoUserExistsException();
		}
		return userRepository.findByEmail(email);
	}
	
//	Update
	public User update(User u) throws NoUserExistsException {
		if(!userRepository.existsById(u.getEmail())) {
			throw new NoUserExistsException();
		}
		
		User u1 = userRepository.findByEmail(u.getEmail());
		u1.setName(u.getName());
//		u1.setEmail(u.getEmail());
		u1.setGender(u.getGender());
		u1.setDOB(u.getDOB());
		u1.setAddress(u.getAddress());
		u1.setType(u.getType());
		u1.setPassword(u.getPassword());
		u1.setContact(u.getContact());
		u1.setActive(u.isActive());
		return userRepository.save(u1);
	}
	
//	Delete All
	public void deleteAll() {
		userRepository.deleteAll();
	}
	
//	Delete by firstName
//	public void delete(String name) {
//		User u = userRepository.findByName(name);
//		userRepository.delete(u);
//	}
	
	public void delete(User u) throws NoUserExistsException
//			throws NoPersonExistsException
	{
		if(!userRepository.existsById(u.getEmail())) {
			throw new NoUserExistsException();
		}
		userRepository.delete(u);
	}
	
//	Count users
	public long countUsers() {
		return userRepository.count();
	}

}
