package com.example.bookService.respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.bookService.model.UserBook;

@Repository
public interface IssueBookRepository extends MongoRepository<UserBook,String>{	
	
	UserBook findByISBNAndEmailAndIsReturned(Long ISBN, String email, boolean isReturn);
}
