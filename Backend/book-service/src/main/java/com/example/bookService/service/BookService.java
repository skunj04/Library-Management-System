package com.example.bookService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookService.exception.BookAlreadyExistsException;
import com.example.bookService.exception.NoBookExistsException;
import com.example.bookService.model.Book;
import com.example.bookService.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
//	Create
	public Book create(Book b) throws BookAlreadyExistsException {
		if(bookRepository.existsById(b.getISBN())) {
			throw new BookAlreadyExistsException();
		}
		Book savedBook= bookRepository.save(b);
		return savedBook;
	}
	
//	Retrieve
	public List<Book> getAll(){
		return bookRepository.findAll();
	}
	
//	Retrieve by firstISBN
	public Book getByISBN(long ISBN) throws NoBookExistsException {
		if(!bookRepository.existsById(ISBN)) {
			throw new NoBookExistsException();
		}
		return bookRepository.findByISBN(ISBN);
	}
	
//	Update
	public Book update(Book b) throws NoBookExistsException {
		if(!bookRepository.existsById(b.getISBN())) {
			throw new NoBookExistsException();
		}
		
		Book b1 = bookRepository.findByISBN(b.getISBN());
		b1.setName(b.getName());
		b1.setDescription(b.getDescription());
		b1.setAuthorName(b.getAuthorName());
		b1.setCategory(b.getCategory());
		b1.setName(b.getName());
		b1.setQuantity(b.getQuantity());
//		b1.setISBN(b.getISBN());
		return bookRepository.save(b1);
	}
	
//	Delete All
	public void deleteAll() {
		bookRepository.deleteAll();
	}

	public void delete(Book b) throws NoBookExistsException{
		if(!bookRepository.existsById(b.getISBN())) {
			throw new NoBookExistsException();
		}
		bookRepository.delete(b);
	}
	
//	Count Books
	public long countBooks() {
		return bookRepository.count();
	}

}
