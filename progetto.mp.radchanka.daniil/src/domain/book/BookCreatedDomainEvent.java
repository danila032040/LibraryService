package domain.book;

import base.ddd.DomainEvent;

public class BookCreatedDomainEvent implements DomainEvent {

	private BookId bookId;

	public BookCreatedDomainEvent(BookId id) {
		this.bookId = id;
	}

	public BookId getId() {
		return bookId;
	}
}
