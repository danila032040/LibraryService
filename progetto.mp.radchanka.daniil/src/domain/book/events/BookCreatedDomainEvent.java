package domain.book.events;

import base.ddd.DomainEvent;
import domain.book.BookId;

public class BookCreatedDomainEvent implements DomainEvent {

	private BookId bookId;

	public BookCreatedDomainEvent(BookId id) {
		this.bookId = id;
	}

	public BookId getId() {
		return bookId;
	}
}
