package com.example.userService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.userService.exception.NoUserExistsException;
import com.example.userService.exception.UserAlreadyExistsException;
import com.example.userService.model.User;
import com.example.userService.repository.UserRepository;

@SpringBootTest(classes= {UserServiceTest.class})
public class UserServiceTest {
	
	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	public List<User> myUsers;
	
	@BeforeEach
	void setup() {
		myUsers= new ArrayList<User>();
		myUsers.add(new User("Kunj","skunj04@gmail.com","male","04-02-2001","Gujarat","user","kunj@123",8980462225L,false));
		myUsers.add(new User("Abhjit","abhijit@gmail.com","male","04-02-2001","Gujarat","user","abhijit@123",8980462225L,false));

	}

	
	@Test
	@Order(1)
	public void test_getAllUsers() {
		when(userRepository.findAll()).thenReturn(myUsers);
		assertEquals(2,userService.getAll().size());
		
	}

	@Test
	@Order(2)
	void test_createUser() throws UserAlreadyExistsException {
		User u = new User("Kunj","skunj04@gmail.com","male","04-02-2001","Gujarat","user","kunj@123",8980462225L,false);
		when(userRepository.save(u)).thenReturn(u);
		assertEquals(u,userService.create(u));
	}
	
	@Test
	@Order(3)
	void test_updateUser() throws UserAlreadyExistsException {
		User u = new User("Kunj","skunj04@gmail.com","male","04-02-2001","Gujarat","user","kunj@123",8980462225L,false);
		when(userRepository.save(u)).thenReturn(u);
		assertEquals(u,userService.create(u));
	}
	

	@Test
	@Order(4)
	public void test_deleteAllUsers() {
		userService.deleteAll();
		verify(userRepository,times(1)).deleteAll();
	}
	@Test
	@Order(5)
	void test_deleteUser() throws NoUserExistsException, UserAlreadyExistsException {
		User u = new User("Kunj","skunj04@gmail.com","male","04-02-2001","Gujarat","user","kunj@123",8980462225L,false);
	 
		when(userRepository.existsById("skunj04@gmail.com")).thenReturn(true);
		myUsers.add(u);
		userService.delete(myUsers.get(myUsers.indexOf(u)));
		verify(userRepository,times(1)).delete(myUsers.get(myUsers.indexOf(u)));
		
	}
	
	@Test
	@Order(6)
	public void test_count() {
		when(userRepository.count()).thenReturn((long) myUsers.size());
		assertEquals(2,userService.countUsers());
	}
}
