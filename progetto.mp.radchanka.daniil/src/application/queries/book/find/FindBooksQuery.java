package application.queries.book.find;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import application.queries.book.common.BookSortByFieldQueryData;
import application.queries.common.sortData.SortTypeQueryData;
import base.mediator.request.Request;
import base.result.ErrorOr;
import domain.author.AuthorId;
import domain.book.Book;
import domain.library.LibraryId;

public class FindBooksQuery implements Request<ErrorOr<Collection<Book>>> {
    private final Optional<String> name;
    private final Optional<String> genre;
    
    private final Optional<Integer> publicationYearPeriodStart;
    private final Optional<Integer> publicationYearPeriodEnd;
    private final Optional<AuthorId> authorId;
    private final Optional<LibraryId> libraryId;
    private final int pageIndex;
    
    private final int pageSize;
    private final BookSortByFieldQueryData sortByField;
    
    private final SortTypeQueryData sortType;
    private final Optional<BookSortByFieldQueryData> thenSortByField;
    private final Optional<SortTypeQueryData> thenSortType;
    
    public FindBooksQuery(
            Optional<String> name,
            Optional<String> genre,
            Optional<Integer> publicationYearPeriodStart,
            Optional<Integer> publicationYearPeriodEnd,
            Optional<AuthorId> authorId,
            Optional<LibraryId> libraryId,
            int pageIndex,
            int pageSize,
            BookSortByFieldQueryData sortByField,
            SortTypeQueryData sortType,
            Optional<BookSortByFieldQueryData> thenSortByField,
            Optional<SortTypeQueryData> thenSortType) {
        this.name = Objects.requireNonNull(name);
        this.genre = Objects.requireNonNull(genre);
        this.publicationYearPeriodStart = Objects.requireNonNull(publicationYearPeriodStart);
        this.publicationYearPeriodEnd = Objects.requireNonNull(publicationYearPeriodEnd);
        this.authorId = Objects.requireNonNull(authorId);
        this.libraryId = Objects.requireNonNull(libraryId);
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.sortByField = sortByField;
        this.sortType = sortType;
        this.thenSortByField = Objects.requireNonNull(thenSortByField);
        this.thenSortType = Objects.requireNonNull(thenSortType);
    }
    
    public Optional<AuthorId> getAuthorId() {
        return authorId;
    }
    
    public Optional<String> getGenre() {
        return genre;
    }
    
    public Optional<LibraryId> getLibraryId() {
        return libraryId;
    }
    
    public Optional<String> getName() {
        return name;
    }
    
    public int getPageIndex() {
        return pageIndex;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public Optional<Integer> getPublicationYearPeriodEnd() {
        return publicationYearPeriodEnd;
    }
    
    public Optional<Integer> getPublicationYearPeriodStart() {
        return publicationYearPeriodStart;
    }
    
    public BookSortByFieldQueryData getSortByField() {
        return sortByField;
    }
    
    public SortTypeQueryData getSortType() {
        return sortType;
    }
    
    public Optional<BookSortByFieldQueryData> getThenSortByField() {
        return thenSortByField;
    }
    
    public Optional<SortTypeQueryData> getThenSortType() {
        return thenSortType;
    }
}
