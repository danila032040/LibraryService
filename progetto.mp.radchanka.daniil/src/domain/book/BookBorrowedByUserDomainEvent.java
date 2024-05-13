package domain.book;

import base.ddd.DomainEvent;
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
