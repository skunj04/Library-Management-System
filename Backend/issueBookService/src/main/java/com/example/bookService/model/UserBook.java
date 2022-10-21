package com.example.bookService.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("UserBooks")
public class UserBook {
	
	@Id
	private String id;
	private String email,name,bookname;
	private Long ISBN;
	private boolean isReturned;
	private LocalDate returnDate;
	
	public UserBook() {
		
	}
	
	public UserBook(String email, String name, String bookname, Long ISBN) {
		super();
		this.email = email;
		this.name = name;
		this.bookname = bookname;
		this.ISBN = ISBN;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public Long getISBN() {
		return ISBN;
	}
	public void setISBN(Long ISBN) {
		this.ISBN = ISBN;
	}
	
	public boolean getIsReturned() {
		return this.isReturned;
	}
	
	public void setIsReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}
	
	public LocalDate getReturnDate() {
		return this.returnDate;
	}
	
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public String toString() {
		return "UserBook [email=" + email + ", name=" + name + ", bookname=" + bookname + ", ISBN=" + ISBN + "]";
	}
	
}
