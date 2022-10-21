package com.example.userService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userService.exception.NoUserExistsException;
import com.example.userService.exception.UserAlreadyExistsException;
import com.example.userService.model.User;
import com.example.userService.service.UserService;

@CrossOrigin
@RequestMapping("/user")
@RestController
public class UserServiceController {
	
	@Autowired
	private UserService userService;
	
	public UserServiceController(UserService userService) {
		super();
		this.userService = userService;
	}

//	
	@RequestMapping("/create")
	public ResponseEntity<User> create(@RequestBody User u) throws UserAlreadyExistsException{
		User savedper= userService.create(u);
        return new ResponseEntity<>(savedper, HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{emailId}")
	public ResponseEntity<User> getUser(@PathVariable String emailId) throws NoUserExistsException {
//		return userService.getByName(name);
        return new ResponseEntity<User>((User) userService.getByEmail(emailId),HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<User>> getUser() {
//		return userService.getAll();	
        return new ResponseEntity<List<User>>((List<User>)userService.getAll(),HttpStatus.OK);

	}
	
	
	@RequestMapping("/update")
	public ResponseEntity<Void> updateUser(@RequestBody User u) throws Exception {
		userService.update(u);
//		return u1.toString();
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{email}")
	public ResponseEntity<Void> deleteUser(@PathVariable String email) throws NoUserExistsException{

		User user = userService.getByEmail(email);
		userService.delete(user);
//		return "Deleted " + u.getName();	
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("/deleteAll")
	public ResponseEntity<Void> deleteAllUser() {
		userService.deleteAll();
//		return "Deleted All" ;
//        return ResponseEntity.noContent().build();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping("/count")
	public ResponseEntity<Long> countUser(){
		return new ResponseEntity<Long>(userService.countUsers(), HttpStatus.OK);
	}
}
