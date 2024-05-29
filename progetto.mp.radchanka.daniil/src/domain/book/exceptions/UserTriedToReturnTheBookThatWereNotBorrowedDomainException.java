package domain.book.exceptions;

import domain.book.Book;
import domain.user.UserId;

public class UserTriedToReturnTheBookThatWereNotBorrowedDomainException extends Exception {
	private static final long serialVersionUID = 1L;
	private Book book;
	private UserId userIdThatTriedToReturnTheBook;

	public UserTriedToReturnTheBookThatWereNotBorrowedDomainException(Book book,
			UserId userIdThatHadTriedToReturnTheBook) {
		this.book = book;
		this.userIdThatTriedToReturnTheBook = userIdThatHadTriedToReturnTheBook;
	}

	public Book getBook() {
		return book;
	}

	public UserId getUserIdThatHadTriedToReturnTheBook() {
		return userIdThatTriedToReturnTheBook;
	}
}
