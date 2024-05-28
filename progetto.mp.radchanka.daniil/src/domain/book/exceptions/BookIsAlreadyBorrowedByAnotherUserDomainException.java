package domain.book.exceptions;

import domain.book.BookId;
import domain.user.UserId;

public class BookIsAlreadyBorrowedByAnotherUserDomainException
		extends
			Exception {
	private static final long serialVersionUID = 1L;
	private BookId bookId;

	private UserId userIdThatHadTriedToBorrowTheBook;
	private UserId userIdThatHadBorrowedTheBook;

	public BookIsAlreadyBorrowedByAnotherUserDomainException(BookId bookId,
			UserId userIdThatHadTriedToBorrowTheBook,
			UserId userIdThatHadBorrowedTheBook) {
		this.bookId = bookId;
		this.userIdThatHadTriedToBorrowTheBook = userIdThatHadTriedToBorrowTheBook;
		this.userIdThatHadBorrowedTheBook = userIdThatHadBorrowedTheBook;
	}
	public BookId getBookId() {
		return bookId;
	}

	public UserId getUserId() {
		return userIdThatHadTriedToBorrowTheBook;
	}
	public UserId getUserIdThatHadBorrowedTheBook() {
		return userIdThatHadBorrowedTheBook;
	}

}
