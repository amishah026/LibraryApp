package com.library.exception;

//A custom exception
public class BookException extends Exception {

	private static final long serialVersionUID = 1L;

    //Constructs an ImageException with a custom error message.
	public BookException(String message) {
		super(message); // Call the constructor of the Exception class with the provided message.
	}

}


