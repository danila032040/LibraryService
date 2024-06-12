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
import domain.author.AuthorId;
import domain.book.Book;
import domain.book.BookId;
import domain.library.LibraryId;

public class BookSortCriteriaBuilderUnitTests {
    
    @Test
    public void sortBy_WhenAuthorIdAscending_ShouldSortBooksByAuthorId() {
        Book book1 = createBookWithAuthor(1, "Test1", new AuthorId(2));
        Book book2 = createBookWithAuthor(2, "Test2", new AuthorId(1));
        List<Book> books = Arrays.asList(book1, book2);
        BookSortCriteriaBuilder builder = BookSortCriteriaBuilder
                .sortBy(BookSortByFieldQueryData.AuthorId, SortType.Ascending);
        SortCriteria<Book> criteria = builder.build();
        
        books.sort(criteria.getSortComparator());
        
        assertThat(books).containsExactly(book2, book1);
    }
    
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
    public void sortBy_WhenLibraryIdDescending_ShouldSortBooksByLibraryId() {
        Book book1 = createBookWithLibrary(1, "Test1", new LibraryId(1));
        Book book2 = createBookWithLibrary(2, "Test2", new LibraryId(2));
        List<Book> books = Arrays.asList(book1, book2);
        BookSortCriteriaBuilder builder = BookSortCriteriaBuilder
                .sortBy(BookSortByFieldQueryData.LibraryId, SortType.Descending);
        SortCriteria<Book> criteria = builder.build();
        
        books.sort(criteria.getSortComparator());
        
        assertThat(books).containsExactly(book2, book1);
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
    public void sortBy_WhenPublicationYearAscending_ShouldSortBooksByPublicationYear() {
        Book book1 = createBook(1, "Test1", "Genre", 1999);
        Book book2 = createBook(2, "Test2", "Genre", 2000);
        List<Book> books = Arrays.asList(book2, book1);
        BookSortCriteriaBuilder builder = BookSortCriteriaBuilder
                .sortBy(BookSortByFieldQueryData.PublicationYear, SortType.Ascending);
        SortCriteria<Book> criteria = builder.build();
        
        books.sort(criteria.getSortComparator());
        
        assertThat(books).containsExactly(book1, book2);
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
    
    private Book createBookWithAuthor(int id, String name, AuthorId authorId) {
        return Book.createNewBook(new BookId(id), name, "Genre", 0, Optional.of(authorId), Optional.empty());
    }
    
    private Book createBookWithLibrary(int id, String name, LibraryId libraryId) {
        return Book.createNewBook(new BookId(id), name, "Genre", 0, Optional.empty(), Optional.of(libraryId));
    }
}
