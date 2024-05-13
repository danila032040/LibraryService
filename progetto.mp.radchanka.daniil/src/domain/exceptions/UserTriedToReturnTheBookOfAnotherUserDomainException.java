package domain.exceptions;

import domain.book.BookId;
import domain.users.UserId;

public class UserTriedToReturnTheBookOfAnotherUserDomainException
		extends
			Exception {

	private static final long serialVersionUID = 1L;
	private BookId bookId;
	private UserId userThatHadBorrowedTheBook;
	private UserId userIdThatTriedToReturnTheBook;

	public UserTriedToReturnTheBookOfAnotherUserDomainException(BookId id,
			UserId userThatHadReservedTheBook,
			UserId userIdThatTriedToReturnTheBook) {
		this.bookId = id;
		this.userThatHadBorrowedTheBook = userThatHadReservedTheBook;
		this.userIdThatTriedToReturnTheBook = userIdThatTriedToReturnTheBook;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BookId getBookId() {
		return bookId;
	}

	public UserId getUserThatHadBorrowedTheBook() {
		return userThatHadBorrowedTheBook;
	}

	public UserId getUserIdThatTriedToReturnTheBook() {
		return userIdThatTriedToReturnTheBook;
	}
}
