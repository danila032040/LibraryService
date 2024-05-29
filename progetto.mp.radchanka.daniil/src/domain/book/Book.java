package domain.book;

import java.util.Objects;
import java.util.Optional;

import base.cloneable.Cloneable;
import base.ddd.Entity;
import domain.author.AuthorId;
import domain.book.events.BookBorrowedByUserDomainEvent;
import domain.book.events.BookCreatedDomainEvent;
import domain.book.events.BookReturnedByUserDomainEvent;
import domain.book.exceptions.BookIsAlreadyBorrowedByAnotherUserDomainException;
import domain.book.exceptions.UserTriedToReturnTheBookOfAnotherUserDomainException;
import domain.book.exceptions.UserTriedToReturnTheBookThatWereNotBorrowedDomainException;
import domain.library.LibraryId;
import domain.user.UserId;

public class Book extends Entity<BookId> implements Cloneable<Book> {
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
		createdBook
				.registerDomainEvent(new BookCreatedDomainEvent(createdBook));
		return createdBook;
	}

	private Optional<AuthorId> authorId;
	private Optional<UserId> borrowedByUserId;
	private String genre;
	private Optional<LibraryId> libraryId;
	private String name;

	private int publicationYear;

	private Book(BookId id, String name, String genre, int publicationYear,
			Optional<AuthorId> authorId, Optional<LibraryId> libraryId) {
		super(id);
		this.name = Objects.requireNonNull(name);
		this.genre = Objects.requireNonNull(genre);
		if (publicationYear < 0)
			throw new IllegalArgumentException(
					"Publication year must be positive");
		this.publicationYear = publicationYear;
		this.authorId = Objects.requireNonNull(authorId);
		this.libraryId = Objects.requireNonNull(libraryId);
		this.borrowedByUserId = Optional.empty();
	}

	public void borrowByUser(UserId userId)
			throws BookIsAlreadyBorrowedByAnotherUserDomainException {
		if (this.borrowedByUserId.isPresent())
			throw new BookIsAlreadyBorrowedByAnotherUserDomainException(
					this,
					userId,
					this.borrowedByUserId.get());

		this.borrowedByUserId = Optional.of(userId);
		this
				.registerDomainEvent(
						new BookBorrowedByUserDomainEvent(this, userId));
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

	public Optional<AuthorId> getAuthorId() {
		return authorId;
	}

	public String getGenre() {
		return genre;
	}

	public Optional<LibraryId> getLibraryId() {
		return libraryId;
	}

	public String getName() {
		return name;
	}

	public Optional<UserId> getBorrowedByUserId() {
		return borrowedByUserId;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void returnByUser(UserId userId)
			throws UserTriedToReturnTheBookOfAnotherUserDomainException,
			UserTriedToReturnTheBookThatWereNotBorrowedDomainException {
		if (borrowedByUserId.isEmpty())
			throw new UserTriedToReturnTheBookThatWereNotBorrowedDomainException(
					this,
					userId);
		UserId userThatHadBorrowedTheBook = borrowedByUserId.get();
		if (!userThatHadBorrowedTheBook.equals(userId))
			throw new UserTriedToReturnTheBookOfAnotherUserDomainException(
					this,
					userThatHadBorrowedTheBook,
					userId);
		borrowedByUserId = Optional.empty();
		this
				.registerDomainEvent(
						new BookReturnedByUserDomainEvent(this, userId));

	}

	public void setGenre(String genre) {
		this.genre = Objects.requireNonNull(genre);
	}

	public void setName(String name) {
		this.name = Objects.requireNonNull(name);
	}

	public void setPublicationYear(int publicationYear) {
		if (publicationYear < 0)
			throw new IllegalArgumentException(
					"Publication year must be positive");
		this.publicationYear = publicationYear;
	}
}
