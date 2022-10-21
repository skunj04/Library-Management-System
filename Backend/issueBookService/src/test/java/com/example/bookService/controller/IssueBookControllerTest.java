package com.example.bookService.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.bookService.model.Book;
import com.example.bookService.model.User;
import com.example.bookService.model.UserBook;
import com.example.bookService.respository.IssueBookRepository;


@SpringBootTest(classes= {IssueBookControllerTest.class})

public class IssueBookControllerTest {
	@Mock
	private IssueBookRepository issueBookRepository;
	
	@InjectMocks
	private IssueBookController issueBookController;

	List<User> usersList = new ArrayList<User>();
	List<Book> booksList = new ArrayList<Book>();
	
	public Map<String, Object> map = new HashMap<String, Object>();
	


	@BeforeEach
	void init() {
		usersList.add(new User("Kunj","skunj04@gmail.com","male","04-02-2001","Gujarat","user","kunj@123",8980462225L,false));
		usersList.add(new User("Abhjit","abhijit@gmail.com","male","04-02-2001","Gujarat","user","abhijit@123",8980462225L,false));
	
		booksList.add(new Book("The Story of My Experiments with Truth","The Story of My Experiments with Truth is the autobiography of Mahatma Gandhi","Mahatma Gandhi","biography",10,9789390932313L));
		booksList.add(new Book("Let Us C","Let Us C by Yashavant Kanetkar addresses several topics that deal with C programming language.","Yashavant Kanetkar","Programming",10,9788176569408L));
	
		map.put("Users", usersList);
		map.put("Books", booksList);		
	}
	
	@Test
	@Order(1)
	public void test_getAllUserBooks() {
	
		List<UserBook> ubs = new ArrayList<UserBook>();
		ubs.add(new UserBook("skunj04@gmail.com", "Kunj","Experiments", 1234567890123L));
		ubs.add(new UserBook("skunj041@gmail.com", "Kunj","Experiments", 1234567890124L));
		
		when(issueBookRepository.findAll()).thenReturn(ubs);
		assertEquals(2,issueBookRepository.findAll().size());
		
	}
	
	
	@Test
	@Order(2)
	public void test_deleteAllUsers() {
		issueBookRepository.deleteAll();
		verify(issueBookRepository,times(1)).deleteAll();
	}
	
	@Test
	@Order(3)
	public void test_count() {
		when(issueBookRepository.count()).thenReturn((long) map.size());
		assertEquals(2,issueBookController.countUser().getBody());
	}
}
