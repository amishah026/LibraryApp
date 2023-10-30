package com.library.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // Marks the class as a JPA entity, indicating that it is a persistent object
		// that can be stored in a database.
@Table(name = "LIB_BOOKS") // Specifies the name of the database table to which this entity is mapped.

public class Book {
	// Marks the field as the primary key of the entity.
	@Id
	// Specifies that the primary key value is generated by the database through auto_increment
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookId;

	private String imageUrl;
	private String title;
	private String author;
	private Integer pages;
	private LocalDate publishDate;

	// Getter and setter methods for the entity's properties follow
	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

}