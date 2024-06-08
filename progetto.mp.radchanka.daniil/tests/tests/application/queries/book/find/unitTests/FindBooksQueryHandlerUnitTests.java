package tests.application.queries.book.find.unitTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import application.queries.book.common.BookSortByFieldQueryData;
import application.queries.book.find.FindBooksQuery;
import application.queries.book.find.FindBooksQueryHandler;
import application.queries.common.sortData.SortTypeQueryData;
import application.queries.common.sortData.SortTypeQueryDataToSortTypeMapper;
import base.repository.SortType;
import base.result.ErrorOr;
import base.utils.Mapper;
import domain.book.Book;
import domain.book.BookId;
import tests.application.queries.book.find.mocks.BookRepositoryMock;

public class FindBooksQueryHandlerUnitTests {
    
    private BookRepositoryMock bookRepository;
    private Mapper<SortTypeQueryData, SortType> sortTypeMapper;
    private FindBooksQueryHandler handler;
    
    @Before
    public void setUp() {
        bookRepository = new BookRepositoryMock();
        sortTypeMapper = new SortTypeQueryDataToSortTypeMapper();
        handler = new FindBooksQueryHandler(sortTypeMapper, bookRepository);
    }
    
    @Test
    public void handle_WhenBooksMatchCriteria_ShouldReturnBooks() {
        FindBooksQuery query = new FindBooksQuery(
                Optional.of("Test"),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                0,
                10,
                BookSortByFieldQueryData.Name,
                SortTypeQueryData.Ascending,
                Optional.empty(),
                Optional.empty());
        
        Book book = Book.createNewBook(new BookId(1), "Test", "Test", 0, Optional.empty(), Optional.empty());
        bookRepository.addBook(book);
        
        ErrorOr<Collection<Book>> result = handler.handle(query);
        
        assertThat(result.isError()).isFalse();
        assertThat(result.getResult()).hasValueSatisfying(books -> {
            assertThat(books).containsExactly(book);
        });
    }
    
    @Test
    public void handle_WhenNoBooksMatchCriteria_ShouldReturnEmptyCollection() {
        FindBooksQuery query = new FindBooksQuery(
                Optional.of("Nonexistent Test"),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                0,
                10,
                BookSortByFieldQueryData.Name,
                SortTypeQueryData.Ascending,
                Optional.empty(),
                Optional.empty());
        
        ErrorOr<Collection<Book>> result = handler.handle(query);
        
        assertThat(result.isError()).isFalse();
        assertThat(result.getResult()).hasValueSatisfying(books -> {
            assertThat(books).isEmpty();
        });
    }
    
    @Test
    public void handle_WhenSortCriteriaAreApplied_ShouldReturnSortedBooks() {
        FindBooksQuery query = new FindBooksQuery(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                0,
                10,
                BookSortByFieldQueryData.PublicationYear,
                SortTypeQueryData.Descending,
                Optional.of(BookSortByFieldQueryData.Name),
                Optional.of(SortTypeQueryData.Ascending));
        
        Book book1 = Book.createNewBook(new BookId(1), "Test1", "Test", 0, Optional.empty(), Optional.empty());
        Book book2 = Book.createNewBook(new BookId(2), "Test2", "Test", 0, Optional.empty(), Optional.empty());
        
        bookRepository.addBooks(Arrays.asList(book1, book2));
        
        ErrorOr<Collection<Book>> result = handler.handle(query);
        
        assertThat(result.isError()).isFalse();
        assertThat(result.getResult()).hasValueSatisfying(books -> {
            assertThat(books).containsExactly(book1, book2);
        });
    }
    
    @Test
    public void handle_WhenPaginationIsApplied_ShouldReturnPaginatedBooks() {
        FindBooksQuery query = new FindBooksQuery(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                1,
                1,
                BookSortByFieldQueryData.PublicationYear,
                SortTypeQueryData.Ascending,
                Optional.empty(),
                Optional.empty());
        
        Book book1 = Book.createNewBook(new BookId(1), "Test", "Test", 0, Optional.empty(), Optional.empty());
        Book book2 = Book.createNewBook(new BookId(2), "Test", "Test", 0, Optional.empty(), Optional.empty());
        Book book3 = Book.createNewBook(new BookId(3), "Test", "Test", 0, Optional.empty(), Optional.empty());
        Book book4 = Book.createNewBook(new BookId(4), "Test", "Test", 0, Optional.empty(), Optional.empty());
        bookRepository.addBooks(Lists.list(book1, book2, book3, book4));
        
        ErrorOr<Collection<Book>> result = handler.handle(query);
        
        assertThat(result.isError()).isFalse();
        assertThat(result.getResult()).hasValueSatisfying(books -> {
            assertThat(books).containsExactly(book2);
        });
    }
}