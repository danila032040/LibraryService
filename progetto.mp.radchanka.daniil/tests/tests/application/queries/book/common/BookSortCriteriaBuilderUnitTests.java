package tests.application.queries.book.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import application.queries.book.common.BookSortByFieldQueryData;
import application.queries.book.common.BookSortCriteriaBuilder;
import base.repository.SortCriteria;
import base.repository.SortType;
import domain.book.Book;
import domain.book.BookId;

public class BookSortCriteriaBuilderUnitTests {
    
    @Test
    public void sortBy_WhenIdAscending_ShouldSortBooksById() {
        Book book1 = createBook(1, "Test1");
        Book book2 = createBook(2, "Test2");
        List<Book> books = Arrays.asList(book2, book1);
        BookSortCriteriaBuilder builder = BookSortCriteriaBuilder
                .sortBy(BookSortByFieldQueryData.Id, SortType.Ascending);
        SortCriteria<Book> criteria = builder.build();
        
        books.sort(criteria.getSortComparator());
        
        assertThat(books).containsExactly(book1, book2);
    }
    
    @Test
    public void sortBy_WhenNameDescending_ShouldSortBooksByNameDescending() {
        Book book1 = createBook(1, "Test1");
        Book book2 = createBook(2, "Test2");
        List<Book> books = Arrays.asList(book1, book2);
        BookSortCriteriaBuilder builder = BookSortCriteriaBuilder
                .sortBy(BookSortByFieldQueryData.Name, SortType.Descending);
        SortCriteria<Book> criteria = builder.build();
        
        books.sort(criteria.getSortComparator());
        
        assertThat(books).containsExactly(book2, book1);
    }
    
    @Test
    public void thenSortBy_ShouldApplySecondarySort() {
        Book book1 = createBook(1, "Test1", "Test1", 0);
        Book book2 = createBook(2, "Test2", "Test1", 1);
        Book book3 = createBook(3, "Test3", "Test2", 1);
        List<Book> books = Arrays.asList(book1, book2, book3);
        BookSortCriteriaBuilder builder = BookSortCriteriaBuilder
                .sortBy(BookSortByFieldQueryData.Genre, SortType.Ascending)
                .thenSortBy(BookSortByFieldQueryData.PublicationYear, SortType.Descending);
        SortCriteria<Book> criteria = builder.build();
        
        books.sort(criteria.getSortComparator());
        
        assertThat(books).containsExactly(book2, book1, book3);
    }
    
    private Book createBook(int id, String name) {
        return Book.createNewBook(new BookId(id), name, "Genre", 0, Optional.empty(), Optional.empty());
    }
    
    private Book createBook(int id, String name, String genre, int publicationYear) {
        return Book.createNewBook(new BookId(id), name, genre, publicationYear, Optional.empty(), Optional.empty());
    }
}