package domain.book.events;

import base.ddd.DomainEvent;
import domain.book.Book;

public class BookCreatedDomainEvent implements DomainEvent {

	private Book book;

	public BookCreatedDomainEvent(Book book) {
		this.book = book;
	}

	public Book getBook() {
		return book;
	}

}
