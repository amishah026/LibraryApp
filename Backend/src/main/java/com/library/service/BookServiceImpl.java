package com.library.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.dto.BookDTO;
import com.library.entity.Book;
import com.library.exception.BookException;
import com.library.repository.BookRepository;

@Service
@Transactional
public class BookServiceImpl implements BookService {
	
	@Autowired
	BookRepository bookRepository;

	@Override
	public List<BookDTO> getBooks() throws BookException {
		Iterable<Book> books = bookRepository.findAll();
		List<BookDTO> bookDTOs= new ArrayList<>();
		
		books.forEach(b->{
			BookDTO bookDTO = new BookDTO();
			bookDTO.setAuthor(b.getAuthor());
			bookDTO.setBookId(b.getBookId());
			bookDTO.setImageUrl(b.getImageUrl());
			bookDTO.setPages(b.getPages());
			bookDTO.setPublishDate(b.getPublishDate());
			bookDTO.setTitle(b.getTitle());
			bookDTOs.add(bookDTO);
		});
		
		if(bookDTOs.isEmpty()) {
			throw new BookException("Service.BOOKS_NOT_FOUND");
		}
		
		return bookDTOs;
		
	}

	@Override
	public BookDTO getSpecificBook(Integer id) throws BookException {
		BookDTO bookDTO  = new BookDTO();
		
		Book book=bookRepository.findById(id).orElseThrow(()-> new BookException("Service.BOOK_NOT_FOUND"));
		
		bookDTO.setAuthor(book.getAuthor());
		bookDTO.setBookId(book.getBookId());
		bookDTO.setImageUrl(book.getImageUrl());
		bookDTO.setPages(book.getPages());
		bookDTO.setPublishDate(book.getPublishDate());
		bookDTO.setTitle(book.getTitle());
		
		return bookDTO;
	}

	@Override
	public void updateBook(BookDTO bookDTO, Integer id) throws BookException {
		Book book= bookRepository.findById(id).orElseThrow(()-> new BookException("Service.BOOK_NOT_FOUND"));
		
		book.setAuthor(bookDTO.getAuthor());
		book.setImageUrl(bookDTO.getImageUrl());
		book.setPages(bookDTO.getPages());
		book.setPublishDate(bookDTO.getPublishDate());
		book.setTitle(bookDTO.getTitle());
		
		

	}

	@Override
	public BookDTO addBook(BookDTO bookDTO) throws BookException {
		Book book= new Book();
		book.setAuthor(bookDTO.getAuthor());
		book.setImageUrl(bookDTO.getImageUrl());
		book.setPages(bookDTO.getPages());
		book.setPublishDate(bookDTO.getPublishDate());
		book.setTitle(bookDTO.getTitle());
		
		Book book2 = bookRepository.save(book);
		bookDTO.setBookId(book2.getBookId());
		return bookDTO;
	}

	@Override
	public void deleteBook(Integer id) throws BookException {
		bookRepository.findById(id).orElseThrow(()-> new BookException("Service.BOOK_NOT_FOUND"));
		bookRepository.deleteById(id);
		
	}
	
	
	
	
	
	


}
