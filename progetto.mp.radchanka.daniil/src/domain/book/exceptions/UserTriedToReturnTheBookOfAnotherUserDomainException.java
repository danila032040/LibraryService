package domain.book.exceptions;

import domain.book.Book;
import domain.user.UserId;

public class UserTriedToReturnTheBookOfAnotherUserDomainException
		extends
			Exception {

	private static final long serialVersionUID = 1L;
	private final Book book;
	private final UserId userIdThatHadTriedToReturnTheBook;
	private final UserId userThatHadBorrowedTheBook;

	public UserTriedToReturnTheBookOfAnotherUserDomainException(Book book,
			UserId userThatHadReservedTheBook,
			UserId userIdThatHadTriedToReturnTheBook) {
		this.book = book;
		this.userThatHadBorrowedTheBook = userThatHadReservedTheBook;
		this.userIdThatHadTriedToReturnTheBook = userIdThatHadTriedToReturnTheBook;
	}

	public Book getBook() {
		return book;
	}

	public UserId getUserIdThatHadTriedToReturnTheBook() {
		return userIdThatHadTriedToReturnTheBook;
	}

	public UserId getUserThatHadBorrowedTheBook() {
		return userThatHadBorrowedTheBook;
	}
}
