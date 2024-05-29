package domain.book.exceptions;

import domain.book.Book;
import domain.book.BookId;
import domain.user.UserId;

public class UserTriedToReturnTheBookOfAnotherUserDomainException extends Exception {

	private static final long serialVersionUID = 1L;
	private Book book;
	private UserId userThatHadBorrowedTheBook;
	private UserId userIdThatHadTriedToReturnTheBook;

	public UserTriedToReturnTheBookOfAnotherUserDomainException(Book book, UserId userThatHadReservedTheBook,
			UserId userIdThatHadTriedToReturnTheBook) {
		this.book = book;
		this.userThatHadBorrowedTheBook = userThatHadReservedTheBook;
		this.userIdThatHadTriedToReturnTheBook = userIdThatHadTriedToReturnTheBook;
	}

	public Book getBook() {
		return book;
	}

	public UserId getUserThatHadBorrowedTheBook() {
		return userThatHadBorrowedTheBook;
	}

	public UserId getUserIdThatHadTriedToReturnTheBook() {
		return userIdThatHadTriedToReturnTheBook;
	}
}
