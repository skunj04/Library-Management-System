package com.example.bookService.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookService.exception.BookAlreadyExistsException;
import com.example.bookService.exception.NoBookExistsException;
import com.example.bookService.model.Book;
import com.example.bookService.service.BookService;

@CrossOrigin
@RequestMapping("/book")
@RestController
public class BookServiceController {
	
	@Autowired
	private BookService bookService;

	public BookServiceController(BookService bookService) {
		super();
		this.bookService = bookService;
	}
	
	@RequestMapping("/create")
	public ResponseEntity<Book> create(@RequestBody Book b) throws BookAlreadyExistsException 
	{
		Book savedBook= bookService.create(b);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{isbn}")
	public ResponseEntity<Book> getBook(@PathVariable long isbn) throws NoBookExistsException {
//		return bookService.getByISBN(ISBN);
        return new ResponseEntity<Book>((Book) bookService.getByISBN(isbn),HttpStatus.OK);

	}
	
	@RequestMapping("/getAll")
	public ResponseEntity<List<Book>> getBook() {
//		return bookService.getAll();
        return new ResponseEntity<List<Book>>((List<Book>)bookService.getAll(),HttpStatus.OK);

	}
	
	@RequestMapping("/update")
	public ResponseEntity<Book> updateBook(@RequestBody Book b) throws NoBookExistsException {
		bookService.update(b);
//		return b1.toString();
		return new ResponseEntity<Book>(b,HttpStatus.OK);
		
	}
	
	@RequestMapping("/delete/{isbn}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long isbn) throws NoBookExistsException{
		Book book = bookService.getByISBN(isbn);
		bookService.delete(book);
//		return "Deleted " + b.getName();	
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping("/deleteAll")
	public ResponseEntity<Void> deleteAllBook() {
		bookService.deleteAll();
//		return "Deleted All" ;
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
	
	@RequestMapping("/count")
	public ResponseEntity<Long> countUser(){
		return new ResponseEntity<Long>(bookService.countBooks(), HttpStatus.OK);
	}

}
