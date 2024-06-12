package tests.application.queries.book.getById.unitTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import application.queries.book.getById.GetBookByIdQuery;
import application.queries.book.getById.GetBookByIdQueryHandler;
import base.result.ErrorOr;
import domain.book.Book;
import domain.book.BookId;
import tests.application.queries.book.getById.mocks.BookRepositoryMock;

public class GetBookByIdQueryHandlerUnitTests {
    
    private BookRepositoryMock bookRepository;
    private GetBookByIdQueryHandler handler;
    
    @Test
    public void handle_WhenBookDoesNotExist_ShouldReturnEmpty() {
        GetBookByIdQuery query = new GetBookByIdQuery(1);
        bookRepository.setBook(Optional.empty());
        
        ErrorOr<Optional<Book>> result = handler.handle(query);
        
        assertThat(result.isError()).isFalse();
        assertThat(result.getResult().get()).isEmpty();
    }
    
    @Test
    public void handle_WhenBookExists_ShouldReturnBook() {
        GetBookByIdQuery query = new GetBookByIdQuery(1);
        Book expectedBook = Book.createNewBook(new BookId(1), "Test", "Test", 0, Optional.empty(), Optional.empty());
        bookRepository.setBook(Optional.of(expectedBook));
        
        ErrorOr<Optional<Book>> result = handler.handle(query);
        
        assertThat(result.isError()).isFalse();
        assertThat(result.getResult()).isPresent();
        assertThat(result.getResult().get()).hasValue(expectedBook);
    }
    
    @Before
    public void setUp() {
        bookRepository = new BookRepositoryMock();
        handler = new GetBookByIdQueryHandler(bookRepository);
    }
}
