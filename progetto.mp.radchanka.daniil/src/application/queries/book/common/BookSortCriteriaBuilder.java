package application.queries.book.common;

import java.util.Comparator;

import base.repository.SortCriteria;
import base.repository.SortType;
import base.utils.ComparatorUtils;
import domain.book.Book;

public class BookSortCriteriaBuilder {
    
    public static BookSortCriteriaBuilder sortBy(BookSortByFieldQueryData sortByField, SortType sortType) {
        Comparator<Book> comparator = createComparatorFrom(sortByField, sortType);
        
        return new BookSortCriteriaBuilder(SortCriteria.sortUsingComparator(comparator));
    }
    
    private static Comparator<Book> createComparatorFrom(BookSortByFieldQueryData sortByField) {
        switch (sortByField) {
        case AuthorId:
            return ComparatorUtils.comparingOptionalField(Book::getAuthorId);
        case Genre:
            return Comparator.comparing(Book::getGenre);
        case Id:
            return Comparator.comparing(Book::getId);
        case LibraryId:
            return ComparatorUtils.comparingOptionalField(Book::getLibraryId);
        case Name:
            return Comparator.comparing(Book::getName);
        case PublicationYear:
            return Comparator.comparing(Book::getPublicationYear);
        default:
            throw new IllegalArgumentException("Unknown enum: " + sortByField);
        }
    }
    
    private static Comparator<Book> createComparatorFrom(BookSortByFieldQueryData sortByField, SortType sortType) {
        Comparator<Book> comparator = createComparatorFrom(sortByField);
        return sortType == SortType.Ascending ? comparator : comparator.reversed();
    }
    
    private final SortCriteria<Book> sortCriteria;
    
    private BookSortCriteriaBuilder(SortCriteria<Book> sortCriteria) {
        this.sortCriteria = sortCriteria;
    }
    
    public SortCriteria<Book> build() {
        return sortCriteria;
    }
    
    public BookSortCriteriaBuilder thenSortBy(BookSortByFieldQueryData sortByField, SortType sortType) {
        Comparator<Book> thenComparator = createComparatorFrom(sortByField, sortType);
        
        sortCriteria.thenSortUsingComparator(thenComparator);
        return this;
    }
    
}
