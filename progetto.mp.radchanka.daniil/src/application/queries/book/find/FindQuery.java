package application.queries.book.find;

import java.util.Collection;
import java.util.Optional;

import application.queries.common.sortData.SortTypeQueryData;
import base.mediator.request.Request;
import base.result.ErrorOr;
import domain.author.AuthorId;
import domain.book.Book;
import domain.library.LibraryId;

public class FindQuery implements Request<ErrorOr<Collection<Book>>> {
	private final Optional<String> name;
	private final Optional<String> genre;
	private final Optional<Integer> publicationYearPeriodStart;
	private final Optional<Integer> publicationYearPeriodEnd;
	private final Optional<AuthorId> authorId;
	private final Optional<LibraryId> libraryId;

	private final int pageIndex;
	private final int pageSize;

	private final BookSortByField sortByField;
	private final SortTypeQueryData sortType;
	private final Optional<BookSortByField> thenSortByField;
	private final Optional<SortTypeQueryData> thenSortType;
	public FindQuery(Optional<String> name, Optional<String> genre,
			Optional<Integer> publicationYearPeriodStart,
			Optional<Integer> publicationYearPeriodEnd,
			Optional<AuthorId> authorId, Optional<LibraryId> libraryId,
			int pageIndex, int pageSize, BookSortByField sortByField,
			SortTypeQueryData sortType,
			Optional<BookSortByField> thenSortByField,
			Optional<SortTypeQueryData> thenSortType) {
		this.name = name;
		this.genre = genre;
		this.publicationYearPeriodStart = publicationYearPeriodStart;
		this.publicationYearPeriodEnd = publicationYearPeriodEnd;
		this.authorId = authorId;
		this.libraryId = libraryId;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.sortByField = sortByField;
		this.sortType = sortType;
		this.thenSortByField = thenSortByField;
		this.thenSortType = thenSortType;
	}
	public Optional<String> getName() {
		return name;
	}
	public Optional<String> getGenre() {
		return genre;
	}
	public Optional<Integer> getPublicationYearPeriodStart() {
		return publicationYearPeriodStart;
	}
	public Optional<Integer> getPublicationYearPeriodEnd() {
		return publicationYearPeriodEnd;
	}
	public Optional<AuthorId> getAuthorId() {
		return authorId;
	}
	public Optional<LibraryId> getLibraryId() {
		return libraryId;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public BookSortByField getSortByField() {
		return sortByField;
	}
	public SortTypeQueryData getSortType() {
		return sortType;
	}
	public Optional<BookSortByField> getThenSortByField() {
		return thenSortByField;
	}
	public Optional<SortTypeQueryData> getThenSortType() {
		return thenSortType;
	}
}
