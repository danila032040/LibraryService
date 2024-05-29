package domain.user.exceptions;

import domain.book.BookId;
import domain.user.User;

public class BookWasNotBorrowedByTheUserDomainEvent extends Exception {
	private static final long serialVersionUID = 1L;

	private User user;
	private BookId bookIdThatWasAlreadyBorrowed;

	public BookWasNotBorrowedByTheUserDomainEvent(User user,
			BookId bookIdThatWasAlreadyBorrowed) {
		super();
		this.user = user;
		this.bookIdThatWasAlreadyBorrowed = bookIdThatWasAlreadyBorrowed;
	}

	public User getUser() {
		return user;
	}

	public BookId getBookIdThatWasNotBorrowed() {
		return bookIdThatWasAlreadyBorrowed;
	}
}