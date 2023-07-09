package com.library.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.BookDTO;
import com.library.service.BookService;


	@RestController
	@RequestMapping(value = "/library")
	public class restAPI {
		@Autowired
		BookService bookService;
		
		@Autowired
		Environment environment;
		
		@CrossOrigin(origins = "http://localhost:4200")
		@GetMapping(value = "/books")
		public ResponseEntity<List<BookDTO>> getBooks() throws Exception{
			List<BookDTO> bookDTOs = bookService.getBooks();
			
			return new ResponseEntity<List<BookDTO>>(bookDTOs, HttpStatus.OK);
			
		}
		
		@CrossOrigin(origins = "http://localhost:4200")
		@GetMapping(value = "/book/{id}")
		public ResponseEntity<BookDTO> getBook(@PathVariable Integer id) throws Exception{
			BookDTO bookDTO = bookService.getSpecificBook(id);
			
			return new ResponseEntity<>(bookDTO, HttpStatus.OK);
			
		}
		

		@CrossOrigin(origins = "http://localhost:4200")
		@PutMapping(value = "/book/{id}")
		public ResponseEntity<String> updateBook(@Valid @RequestBody BookDTO bookDTO, @PathVariable Integer id) throws Exception{
			bookService.updateBook(bookDTO, id);
			String message = environment.getProperty("API.UPDATE_BOOK");
			
			return new ResponseEntity<>(message, HttpStatus.OK);
			
		}
		
		@CrossOrigin(origins = "http://localhost:4200")
		@PostMapping(value = "/addbook")
		public ResponseEntity<String> addBook(@Valid @RequestBody BookDTO bookDTO) throws Exception{
			BookDTO bookDTO2 = bookService.addBook(bookDTO);
			String message = environment.getProperty("API.ADD_BOOK") +" "+ bookDTO2.getBookId();
			
			return new ResponseEntity<>(message, HttpStatus.CREATED);
		}
		
		@CrossOrigin(origins = "http://localhost:4200")
		@DeleteMapping(value = "/book/{id}")
		public ResponseEntity<String> addBook(@PathVariable Integer id) throws Exception{
			bookService.deleteBook(id);
			String message = environment.getProperty("API.DELETE_BOOK") +" "+ id;
			
			return new ResponseEntity<>(message, HttpStatus.OK);
		} 
		
		
		


	}

