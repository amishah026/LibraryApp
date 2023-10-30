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

@Service // Indicates that this class is a Spring service component.
@Transactional // Enforces a transactional behavior for the methods in this class.
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository; // Autowired dependency injection of BookRepository.

	// Retrieve a list of all books
	@Override
	public List<BookDTO> getBooks() throws BookException {
		Iterable<Book> books = bookRepository.findAll();
		List<BookDTO> bookDTOs = new ArrayList<>();

		// Convert Book entities to BookDTOs
		books.forEach(b -> {
			BookDTO bookDTO = BookDTO.EntityToDto(b);
			bookDTOs.add(bookDTO);
		});

		// If no books are found, throw an exception
		if (bookDTOs.isEmpty()) {
			throw new BookException("Service.BOOKS_NOT_FOUND");
		}
		return bookDTOs;
	}

	// Retrieve a specific book by ID
	@Override
	public BookDTO getSpecificBook(Integer id) throws BookException {

		// Find the book by ID or throw an exception if not found
		Book book = bookRepository.findById(id).orElseThrow(() -> new BookException("Service.BOOK_NOT_FOUND"));
		// Convert the Book entity to a BookDTO
		BookDTO bookDTO = BookDTO.EntityToDto(book);

		return bookDTO;
	}

	// Update book information by ID
	@Override
	public void updateBook(BookDTO bookDTO, Integer id) throws BookException {
		// Find the book by ID or throw an exception if not found
		Book book = bookRepository.findById(id).orElseThrow(() -> new BookException("Service.BOOK_NOT_FOUND"));
		// Update the book properties with the values from the DTO
		book.setAuthor(bookDTO.getAuthor());
		book.setImageUrl(bookDTO.getImageUrl());
		book.setPages(bookDTO.getPages());
		book.setPublishDate(bookDTO.getPublishDate());
		book.setTitle(bookDTO.getTitle());
	}

	// Add a new book
	@Override
	public BookDTO addBook(BookDTO bookDTO) throws BookException {
		// Convert the BookDTO to a Book entity
		Book book = BookDTO.DtoToEntity(bookDTO);
		// Save the book entity in the repository and update the BookDTO with the
		// generated ID
		Book book2 = bookRepository.save(book);
		bookDTO.setBookId(book2.getBookId());
		return bookDTO;
	}

	// Delete a book by ID
	@Override
	public void deleteBook(Integer id) throws BookException {
		// Find the book by ID or throw an exception if not found
		bookRepository.findById(id).orElseThrow(() -> new BookException("Service.BOOK_NOT_FOUND"));
		bookRepository.deleteById(id);
	}
}
