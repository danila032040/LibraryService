package domain.book.exceptions;

import domain.book.Book;
import domain.user.UserId;

public class BookIsAlreadyBorrowedByAnotherUserDomainException
		extends
			Exception {
	private static final long serialVersionUID = 1L;
	private final Book book;
	private final UserId userIdThatHadBorrowedTheBook;
	private final UserId userIdThatHadTriedToBorrowTheBook;

	public BookIsAlreadyBorrowedByAnotherUserDomainException(Book book,
			UserId userIdThatHadTriedToBorrowTheBook,
			UserId userIdThatHadBorrowedTheBook) {
		this.book = book;
		this.userIdThatHadTriedToBorrowTheBook = userIdThatHadTriedToBorrowTheBook;
		this.userIdThatHadBorrowedTheBook = userIdThatHadBorrowedTheBook;
	}

	public Book getBook() {
		return book;
	}

	public UserId getUserIdThatHadBorrowedTheBook() {
		return userIdThatHadBorrowedTheBook;
	}

	public UserId getUserIdThatHadTriedToBorrowTheBook() {
		return userIdThatHadTriedToBorrowTheBook;
	}
}
