package domain.book.exceptions;

import domain.book.Book;
import domain.user.UserId;

public class BookIsAlreadyBorrowedByAnotherUserDomainException extends Exception {
	private static final long serialVersionUID = 1L;
	private Book book;

	private UserId userIdThatHadTriedToBorrowTheBook;
	private UserId userIdThatHadBorrowedTheBook;

	public BookIsAlreadyBorrowedByAnotherUserDomainException(Book book, UserId userIdThatHadTriedToBorrowTheBook,
			UserId userIdThatHadBorrowedTheBook) {
		this.book = book;
		this.userIdThatHadTriedToBorrowTheBook = userIdThatHadTriedToBorrowTheBook;
		this.userIdThatHadBorrowedTheBook = userIdThatHadBorrowedTheBook;
	}

	public Book getBook() {
		return book;
	}

	public UserId getUserIdThatHadTriedToBorrowTheBook() {
		return userIdThatHadTriedToBorrowTheBook;
	}

	public UserId getUserIdThatHadBorrowedTheBook() {
		return userIdThatHadBorrowedTheBook;
	}
}
