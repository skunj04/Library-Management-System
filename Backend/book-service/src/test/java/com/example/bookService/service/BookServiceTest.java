package com.example.bookService.service;

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


import com.example.bookService.exception.BookAlreadyExistsException;
import com.example.bookService.exception.NoBookExistsException;
import com.example.bookService.model.Book;
import com.example.bookService.repository.BookRepository;

@SpringBootTest(classes= {BookServiceTest.class})
public class BookServiceTest {
	
	@InjectMocks
	private BookService bookService;
	
	@Mock
	private BookRepository bookRepository;
	
	public List<Book> myBooks;
	
	@BeforeEach
	void setup() {
		myBooks= new ArrayList<Book>();
		myBooks.add(new Book("The Story of My Experiments with Truth","The Story of My Experiments with Truth is the autobiography of Mahatma Gandhi","Mahatma Gandhi","biography",10,9789390932313L));
		myBooks.add(new Book("Let Us C","Let Us C by Yashavant Kanetkar addresses several topics that deal with C programming language.","Yashavant Kanetkar","Programming",10,9788176569408L));

	}

	
	@Test
	@Order(1)
	public void test_getAllBooks() {
		when(bookRepository.findAll()).thenReturn(myBooks);
		assertEquals(2,bookService.getAll().size());
		
	}

	@Test
	@Order(2)
	void test_createBook() throws BookAlreadyExistsException {
		Book b = new Book("The Story of My Experiments with Truth","The Story of My Experiments with Truth is the autobiography of Mahatma Gandhi","Mahatma Gandhi","biography",10,9789390932313L);
		when(bookRepository.save(b)).thenReturn(b);
		assertEquals(b,bookService.create(b));
	}

	
	@Test
	@Order(3)
	void test_updateBook() throws BookAlreadyExistsException {
		Book b = new Book("The Story of My Experiments with Truth","The Story of My Experiments with Truth is the autobiography of Mahatma Gandhi","Mahatma Gandhi","biography",10,9789390932313L);
		when(bookRepository.save(b)).thenReturn(b);
		assertEquals(b,bookService.create(b));
	}
	
	@Test
	@Order(4)
	public void test_deleteAllBooks() {
		bookService.deleteAll();
		verify(bookRepository,times(1)).deleteAll();
	}
	@Test
	@Order(5)
	void test_deleteBook() throws NoBookExistsException {
		Book b = (new Book("The Story of My Experiments with Truth","The Story of My Experiments with Truth is the autobiography of Mahatma Gandhi","Mahatma Gandhi","biography",10,9789390932313L));
		when(bookRepository.existsById(9789390932313L)).thenReturn(true);
		myBooks.add(b);
		bookService.delete(myBooks.get(myBooks.indexOf(b)));
		verify(bookRepository,times(1)).delete(myBooks.get(myBooks.indexOf(b)));
	}
	
	@Test
	@Order(6)
	public void test_count() {
		when(bookRepository.count()).thenReturn((long) myBooks.size());
		assertEquals(2,bookService.countBooks());
	}

}
