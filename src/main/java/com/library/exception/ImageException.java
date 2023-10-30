package com.library.exception;

// A custom exception
public class ImageException extends Exception {
    // A unique identifier for the class version, used for serialization
    private static final long serialVersionUID = 1L;

    //Constructs an ImageException with a custom error message.
    public ImageException(String message) {
        super(message); // Call the constructor of the Exception class with the provided message.
    }
}
