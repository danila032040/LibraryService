package domain.book.events;

import base.ddd.DomainEvent;
import domain.book.Book;
import domain.user.UserId;

public class BookReturnedByUserDomainEvent implements DomainEvent {
	private Book returnedBook;
	private UserId userIdThatHadReturnedTheBook;

	public BookReturnedByUserDomainEvent(Book borrowedBook,
			UserId userIdThatHadBorrowedTheBook) {
		this.returnedBook = borrowedBook;
		this.userIdThatHadReturnedTheBook = userIdThatHadBorrowedTheBook;

	}

	public Book getBorrowedBook() {
		return returnedBook;
	}

	public UserId getUserIdThatHadBorrowedTheBook() {
		return userIdThatHadReturnedTheBook;
	}
}
