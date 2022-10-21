package com.example.bookService.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.bookService.model.Book;
import com.example.bookService.model.User;
import com.example.bookService.model.UserBook;
import com.example.bookService.exception.NotFoundException;
import com.example.bookService.respository.IssueBookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RequestMapping("/issue")
@RestController
public class IssueBookController {
	
	@Autowired
	private RestTemplate restTemplate;

	List<User> userList= new ArrayList<User>();
	List<Book> bookList = new ArrayList<Book>();
	 
	@Autowired
	private IssueBookRepository issueBookRepository;
	 
//	Select users and books to issue
 	@RequestMapping("/select")
	public ResponseEntity<?> getUsers(){
		ResponseEntity<User[]> userResponse= restTemplate.getForEntity("http://localhost:8081/user/getAll", User[].class);
		ResponseEntity<Book[]> bookResponse =  restTemplate.getForEntity("http://localhost:8082/book/getAll", Book[].class);

		User[] users = userResponse.getBody();
		Book[] books = bookResponse.getBody();
		
		userList = Arrays.asList(users);
		bookList = Arrays.asList(books);

		List<User> usersList = new ArrayList<User>();
		List<Book> booksList = new ArrayList<Book>();
		
		for (User user : userList) {
			if(user.isActive() == true) {
				usersList.add(user);
			}
		}
		for (Book book : books) {
			if(book.getQuantity() > 0) {
				booksList.add(book);
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("Users", usersList);
		map.put("Books", booksList);
		
		
        return new ResponseEntity<>(map, HttpStatus.OK);
	}
 	
// 	Issue book to user
 	@RequestMapping("/issue")
 	public ResponseEntity<?> issueBooks(@RequestBody UserBook ub1) throws JsonProcessingException{
 		UserBook ub = new UserBook();
 		
 		System.out.println(ub1.getISBN());

 		for (User user : userList) {
			if((user.getEmail().matches(ub1.getEmail()))) {
				ub.setEmail(ub1.getEmail());
				ub.setName(user.getName());
			}
		}
 		
 		System.out.println(ub);
 		for (Book book : bookList) {
			if(book.getISBN().equals(ub1.getISBN())) {
				ub.setISBN(ub1.getISBN());
				ub.setBookname(book.getName());
			}
		}
 		ub.setIsReturned(false);
 		ub.setReturnDate(LocalDate.now().plusDays(2));
 		
 		issueBookRepository.save(ub);
 		
		ResponseEntity<Book> bookResponse =  restTemplate.getForEntity("http://localhost:8082/book/get/" + ub.getISBN(), Book.class);
		Book book = bookResponse.getBody();
		book.setQuantity(book.getQuantity() - 1);
		
		String json = new ObjectMapper().writeValueAsString(book);

		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
		
		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		
		Book updatedBook = restTemplate.postForObject("http://localhost:8082/book/update?ISBN=" + ub.getISBN(), entity, Book.class);
		System.out.println(updatedBook);
		
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
 	}
 
// 	Return issued book
 	@RequestMapping("/return")
 	public ResponseEntity<?> returnBooks(@RequestBody UserBook ub1) throws JsonProcessingException{
 		
		ResponseEntity<Book> bookResponse =  restTemplate.getForEntity("http://localhost:8082/book/get/" + ub1.getISBN(), Book.class);
		Book book = bookResponse.getBody();
		book.setQuantity(book.getQuantity() + 1);
		
		String json = new ObjectMapper().writeValueAsString(book);

		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
		
		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		
		Book updatedBook = restTemplate.postForObject("http://localhost:8082/book/update?ISBN=" + ub1.getISBN(), entity, Book.class);
		System.out.println(updatedBook);
		
		ResponseEntity<UserBook>updatedUserBook = updateUserBooks(ub1.getId());
		
		
        return new ResponseEntity<>(updatedUserBook, HttpStatus.OK);
		
 	}
 	
// 	get all the issued book
	@RequestMapping("/getAll")
	public ResponseEntity<List<UserBook>> getUserBooks() {
        return new ResponseEntity<List<UserBook>>((List<UserBook>)issueBookRepository.findAll(),HttpStatus.OK);
	}
	
//	Update user books
	public ResponseEntity<UserBook> updateUserBooks(String id) throws JsonProcessingException{
//		System.out.println(id);
		UserBook ub= restTemplate.getForObject("http://localhost:8083/issue/get/" + id, UserBook.class);
		ub.setIsReturned(true);
		ub.setReturnDate(LocalDate.now());		
		return new ResponseEntity<UserBook>(issueBookRepository.save(ub),HttpStatus.OK);
	}
	
//	Retrieve user books by id 
	@RequestMapping("/get/{id}")
	public ResponseEntity<UserBook> getUserBook(@PathVariable String id) throws NotFoundException {
		
		UserBook result = issueBookRepository.findById(id).orElseThrow(() -> new NotFoundException("Issued book not found"));
        return new ResponseEntity<UserBook>(result, HttpStatus.OK);
	}
	
//	Delete All issued books
	@RequestMapping("/deleteAll")
	public ResponseEntity<Void> deleteUserBook(){
		issueBookRepository.deleteAll();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
//	Count issued books
	@RequestMapping("/count")
	public ResponseEntity<Long> countUser(){
		return new ResponseEntity<Long>(issueBookRepository.count(), HttpStatus.OK);
	}

}
