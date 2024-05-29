package domain.book.events;

import base.ddd.DomainEvent;
import domain.book.Book;
import domain.user.UserId;

public class BookReturnedByUserDomainEvent implements DomainEvent {
	private final Book returnedBook;
	private final UserId userIdThatHadReturnedTheBook;

	public BookReturnedByUserDomainEvent(Book returnedBook,
			UserId userIdThatHadReturnedTheBook) {
		this.returnedBook = returnedBook;
		this.userIdThatHadReturnedTheBook = userIdThatHadReturnedTheBook;
	}

	public Book getReturnedBook() {
		return returnedBook;
	}

	public UserId getUserIdThatHadReturnedTheBook() {
		return userIdThatHadReturnedTheBook;
	}
}
