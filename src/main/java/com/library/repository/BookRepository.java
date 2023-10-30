package com.library.repository;

import org.springframework.data.repository.CrudRepository;

import com.library.entity.Book;

//This interface extends CrudRepository to perform database operations on Book entities.
// It provides basic CRUD (Create, Read, Update, Delete) functionality for the Book entity.
public interface BookRepository extends CrudRepository<Book	, Integer> {

}

