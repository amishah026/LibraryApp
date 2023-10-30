package com.library.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.library.entity.Image;

//This interface extends CrudRepository to perform database operations on Image entities.
//It provides basic CRUD (Create, Read, Update, Delete) functionality for the Image entity.
public interface ImageRepository extends CrudRepository<Image, Long> {

	// SpringData- Query creation based on the method name
	Optional<Image> findByName(String name);
}
