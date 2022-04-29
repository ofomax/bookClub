package com.codingdojo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.models.Book;

@Repository
public interface BookRepository extends CrudRepository <Book, Long> {
	List<Book>findAll();
	List<Book> findAllBooksByUser_Id(Long user_id);
}
