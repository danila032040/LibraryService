package domain.book.events;

import base.ddd.DomainEvent;
import domain.book.BookId;
import domain.users.UserId;

public class BookReturnedByUserDomainEvent implements DomainEvent {
	private BookId returnedBookId;
	private UserId userIdThatHadReturnedTheBook;

	public BookReturnedByUserDomainEvent(BookId borrowedBookId,
			UserId userIdThatHadBorrowedTheBook) {
		this.returnedBookId = borrowedBookId;
		this.userIdThatHadReturnedTheBook = userIdThatHadBorrowedTheBook;

	}

	public BookId getBorrowedBookId() {
		return returnedBookId;
	}

	public UserId getUserIdThatHadBorrowedTheBook() {
		return userIdThatHadReturnedTheBook;
	}
}
