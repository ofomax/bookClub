package com.codingdojo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.models.Book;

import com.codingdojo.repositories.BookRepository;

@Service
public class BookService {
	
	private final BookRepository bookRepository;
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
//____________________________
//			GET ALL BOOKS
//___________________________
	
	public List<Book> allBooks(){
		return bookRepository.findAll();
	}
	
	
	
//____________________________
//			CREATE BOOK
//___________________________
	public Book createBook(Book c) {
		return bookRepository.save(c);
	}
	
//____________________________
//			RENDER A BOOK
//___________________________
	public Book showOne(Long id) {
		Optional<Book> book = bookRepository.findById(id);
		return book.isPresent()?book.get():null;
	}
	
	
//____________________________
//			UPDATE A BOOK
//___________________________
	public Book updateBook(Book c) {
		return bookRepository.save(c);
	}
	
//____________________________
//			SEARCH FOR BOOKS
//___________________________
	public List<Book> searchFor(Long id){
		return bookRepository.findAllBooksByUser_Id(id);
	}

}
