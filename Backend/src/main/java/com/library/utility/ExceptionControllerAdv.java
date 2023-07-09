package com.library.utility;

import java.time.LocalDateTime;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.library.exception.BookException;
import com.library.exception.ImageException;



@RestControllerAdvice
public class ExceptionControllerAdv {
	@Autowired
	Environment environment;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Info> handlegeneralExcpetion(Exception exception){
		Info info = new Info();
		info.setErrorMessage(environment.getProperty("General.MESSAGE" + exception.getMessage()));
		info.setDateTime(LocalDateTime.now());

		return new ResponseEntity<>(info, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BookException.class)
	public ResponseEntity<Info> handlebookExcpetion(BookException exception){
		Info info = new Info();
		info.setErrorMessage(environment.getProperty(exception.getMessage()));
		info.setDateTime(LocalDateTime.now());

		return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ImageException.class)
	public ResponseEntity<Info> handlebookExcpetion(ImageException exception){
		Info info = new Info();
		info.setErrorMessage(environment.getProperty(exception.getMessage()));
		info.setDateTime(LocalDateTime.now());

		return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Info> handleMethodArgumentExcpetion(MethodArgumentNotValidException exception){
		Info info = new Info();
		String message = "";
		message = exception.getBindingResult().getAllErrors().stream().map(err->err.getDefaultMessage()).collect(Collectors.joining(", "));
		info.setErrorMessage(message);
		info.setDateTime(LocalDateTime.now());

		return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST);
	}



}
