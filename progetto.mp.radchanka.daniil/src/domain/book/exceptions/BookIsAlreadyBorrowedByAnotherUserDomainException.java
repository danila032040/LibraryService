package domain.book.exceptions;

import domain.book.BookId;
import domain.users.UserId;

public class BookIsAlreadyBorrowedByAnotherUserDomainException
		extends
			Exception {
	private static final long serialVersionUID = 1L;
	private BookId bookId;

	private UserId userIdThatHadTriedToBorrowedTheBook;
	private UserId userIdThatHadBorrowedTheBook;

	public BookIsAlreadyBorrowedByAnotherUserDomainException(BookId bookId,
			UserId userIdThatHadTriedToBorrowedTheBook,
			UserId userIdThatHadBorrowedTheBook) {
		this.bookId = bookId;
		this.userIdThatHadTriedToBorrowedTheBook = userIdThatHadTriedToBorrowedTheBook;
		this.userIdThatHadBorrowedTheBook = userIdThatHadBorrowedTheBook;
	}
	public BookId getBookId() {
		return bookId;
	}

	public UserId getUserId() {
		return userIdThatHadTriedToBorrowedTheBook;
	}
	public UserId getUserIdThatHadBorrowedTheBook() {
		return userIdThatHadBorrowedTheBook;
	}

}
