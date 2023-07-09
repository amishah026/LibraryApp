package com.library.dto;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;


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
	@Override
	public int hashCode() {
		return Objects.hash(author, bookId, imageUrl, pages, publishDate, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		BookDTO other = (BookDTO) obj;
		return Objects.equals(author, other.author) && Objects.equals(bookId, other.bookId)
				&& Objects.equals(imageUrl, other.imageUrl) && Objects.equals(pages, other.pages)
				&& Objects.equals(publishDate, other.publishDate) && Objects.equals(title, other.title);
	}
	@Override
	public String toString() {
		return "BookDTO [bookId=" + bookId + ", imageUrl=" + imageUrl + ", title=" + title + ", author=" + author
				+ ", pages=" + pages + ", publishDate=" + publishDate + "]";
	}


}
