package domain.book.events;

import base.ddd.DomainEvent;
import domain.book.BookId;
import domain.users.UserId;

public class BookBorrowedByUserDomainEvent implements DomainEvent {
	private BookId borrowedBookId;
	private UserId userIdThatHadBorrowedTheBook;

	public BookBorrowedByUserDomainEvent(BookId borrowedBookId,
			UserId userIdThatHadBorrowedTheBook) {
		this.borrowedBookId = borrowedBookId;
		this.userIdThatHadBorrowedTheBook = userIdThatHadBorrowedTheBook;

	}

	public BookId getBorrowedBookId() {
		return borrowedBookId;
	}

	public UserId getUserIdThatHadBorrowedTheBook() {
		return userIdThatHadBorrowedTheBook;
	}
}
