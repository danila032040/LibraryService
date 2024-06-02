package application.commands.book.update;

import java.util.Optional;

import base.mediator.request.Request;
import base.result.ErrorOr;
import base.result.Success;

public class UpdateBookCommand implements Request<ErrorOr<Success>> {
	private final int bookId;
	private final String name;
	private final String genre;
	private final int publicationYear;
	private final Optional<Integer> authorId;
	private final Optional<Integer> libraryId;

	public UpdateBookCommand(int bookId, String name, String genre,
			int publicationYear, Optional<Integer> authorId,
			Optional<Integer> libraryId) {
		this.bookId = bookId;
		this.name = name;
		this.genre = genre;
		this.publicationYear = publicationYear;
		this.authorId = authorId;
		this.libraryId = libraryId;
	}

	public int getBookId() {
		return bookId;
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
	public Optional<Integer> getAuthorId() {
		return authorId;
	}
	public Optional<Integer> getLibraryId() {
		return libraryId;
	}
}
