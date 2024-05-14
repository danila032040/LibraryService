package domain.book;

import base.ddd.Entity;
import domain.author.AuthorId;
import domain.book.events.BookCreatedDomainEvent;
import domain.book.exceptions.BookIsAlreadyBorrowedByAnotherUserDomainException;
import domain.book.exceptions.UserTriedToReturnTheBookOfAnotherUserDomainException;
import domain.library.LibraryId;
import domain.users.UserId;

import java.util.Optional;

import base.cloneable.Cloneable;

public class Book extends Entity<BookId> implements Cloneable<Book> {
	private String name;
	private String genre;
	private int publicationYear;
	private Optional<AuthorId> authorId;
	private Optional<LibraryId> libraryId;
	private Optional<UserId> borrowedByUserId;

	private Book(BookId id, String name, String genre, int publicationYear,
			Optional<AuthorId> authorId, Optional<LibraryId> libraryId) {
		super(id);
		this.name = name;
		this.genre = genre;
		this.publicationYear = publicationYear;
		this.authorId = authorId;
		this.libraryId = libraryId;
		this.borrowedByUserId = Optional.empty();
	}

	public static Book createNewBook(
			BookId id,
			String name,
			String genre,
			int publicationYear,
			Optional<AuthorId> authorId,
			Optional<LibraryId> libraryId) {
		Book createdBook = new Book(
				id,
				name,
				genre,
				publicationYear,
				authorId,
				libraryId);
		createdBook.registerDomainEvent(new BookCreatedDomainEvent(id));
		return createdBook;
	}

	public String getName() {
		return name;
	}

	public String getGenre() {
		return genre;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public Optional<AuthorId> getAuthorId() {
		return authorId;
	}

	public Optional<LibraryId> getLibraryId() {
		return libraryId;
	}

	public void borrowByUser(UserId userId)
			throws BookIsAlreadyBorrowedByAnotherUserDomainException {
		if (this.borrowedByUserId.isPresent())
			throw new BookIsAlreadyBorrowedByAnotherUserDomainException(
					this.getId(),
					userId,
					this.borrowedByUserId.get());

		this.borrowedByUserId = Optional.of(userId);
	}

	public void returnByUser(UserId userId)
			throws UserTriedToReturnTheBookOfAnotherUserDomainException {
		if (borrowedByUserId.isEmpty())
			return;
		UserId userThatHadBorrowedTheBook = borrowedByUserId.get();
		if (!userThatHadBorrowedTheBook.equals(userId))
			throw new UserTriedToReturnTheBookOfAnotherUserDomainException(
					getId(),
					userId,
					userThatHadBorrowedTheBook);
		borrowedByUserId = Optional.empty();

	}

	@Override
	public Book createClone() {
		return new Book(
				this.getId().createClone(),
				name,
				genre,
				publicationYear,
				authorId.map(AuthorId::createClone),
				libraryId.map(LibraryId::createClone));
	}
}
