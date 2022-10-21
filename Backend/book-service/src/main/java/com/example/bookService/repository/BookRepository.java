package com.example.bookService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.bookService.model.Book;

public interface BookRepository extends MongoRepository<Book, Long>{
	
	public Book findByISBN(long ISBN);

}
