package com.example.bookService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Books")
public class Book {
	
	@Id
	private long ISBN;
	
	private String name, description, authorName, category;
	private int quantity;
	
	public Book(String name, String description, String authorName, String category, int quantity, long ISBN) {
		super();
		this.name = name;
		this.description = description;
		this.authorName = authorName;
		this.category = category;
		this.quantity = quantity;
		this.ISBN = ISBN;
	}
	
//	public String getId() {
//		return id;
//	}
//	
//	public void setId(String id) {
//		this.id = id;
//	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public long getISBN() {
		return ISBN;
	}
	public void setISBN(long iSBN) {
		ISBN = iSBN;
	}
	
	@Override
	public String toString() {
		return "Book [name=" + name + ", description=" + description + ", author_name=" + authorName + ", category="
				+ category + ", quantity=" + quantity + ", ISBN=" + ISBN + "]";
	}
	
}
