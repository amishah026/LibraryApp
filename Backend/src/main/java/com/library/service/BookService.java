package com.library.service;

import java.util.List;

import com.library.dto.BookDTO;
import com.library.exception.BookException;

public interface BookService {
	
	public List<BookDTO> getBooks() throws BookException;
	
	
	public BookDTO getSpecificBook(Integer id) throws BookException;

	public void updateBook(BookDTO bookDTO, Integer id) throws BookException;
	
	public BookDTO addBook(BookDTO bookDTO) throws BookException;
	
	public void deleteBook(Integer id) throws BookException;
	
}
