package com.library.dto;

import java.time.LocalDate;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.library.entity.Book;

// BookDTO class-Attributes, getters and setters, bean validation
public class BookDTO {

	private Integer bookId;

	private String imageUrl;

	@NotBlank(message = "{book.title.notblank}")
	private String title;

	@NotBlank(message = "{book.author.notblank}")
	private String author;

	@NotNull(message = "{book.pages.notnull}")
	@Min(value = 1, message = "{book.pages.min}")
	private Integer pages;

	@NotNull(message = "{book.publishDate.notnull}")
	@Past(message = "{book.publishDate.invalid}")
	private LocalDate publishDate;

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
	
	public static Book DtoToEntity(BookDTO bookDTO) {
		Book book = new Book();
		book.setAuthor(bookDTO.getAuthor());
		book.setImageUrl(bookDTO.getImageUrl());
		book.setPages(bookDTO.getPages());
		book.setPublishDate(bookDTO.getPublishDate());
		book.setTitle(bookDTO.getTitle());
		
		return book;
	}
	
	public static BookDTO EntityToDto(Book book) {
		BookDTO bookDTO = new BookDTO();
		bookDTO.setAuthor(book.getAuthor());
		bookDTO.setBookId(book.getBookId());
		bookDTO.setImageUrl(book.getImageUrl());
		bookDTO.setPages(book.getPages());
		bookDTO.setPublishDate(book.getPublishDate());
		bookDTO.setTitle(book.getTitle());
	
		
		return bookDTO;
	}
	

}