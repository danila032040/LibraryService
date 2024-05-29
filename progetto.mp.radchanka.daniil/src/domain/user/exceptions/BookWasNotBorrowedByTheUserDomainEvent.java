package domain.user.exceptions;

import domain.book.BookId;
import domain.user.User;

public class BookWasNotBorrowedByTheUserDomainEvent extends Exception {
	private static final long serialVersionUID = 1L;

	private final User user;
	private final BookId bookIdThatWasAlreadyBorrowed;

	public BookWasNotBorrowedByTheUserDomainEvent(User user,
			BookId bookIdThatWasAlreadyBorrowed) {
		super();
		this.user = user;
		this.bookIdThatWasAlreadyBorrowed = bookIdThatWasAlreadyBorrowed;
	}

	public BookId getBookIdThatWasNotBorrowed() {
		return bookIdThatWasAlreadyBorrowed;
	}

	public User getUser() {
		return user;
	}
}