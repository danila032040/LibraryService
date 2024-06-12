package tests.application.commands.book.register.unitTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import application.commands.book.register.RegisterBookCommand;
import application.commands.book.register.RegisterBookCommandHandler;
import base.result.ErrorOr;
import domain.book.BookId;
import domain.book.events.BookCreatedDomainEvent;
import tests.application.commands.book.register.mocks.BookRepositoryMock;
import tests.application.commands.book.register.mocks.DomainEventPublisherMock;

public class RegisterBookCommandHandlerUnitTests {
    
    private BookRepositoryMock bookRepository;
    private DomainEventPublisherMock domainEventPublisher;
    private RegisterBookCommandHandler handler;
    
    @Test
    public void handle_WhenBookAlreadyExists_ShouldReturnErrorMessage() {
        RegisterBookCommand command = new RegisterBookCommand("Test", "Test", 0, Optional.empty(), Optional.empty());
        bookRepository.setThrowAlreadyExistsException(true);
        
        ErrorOr<BookId> result = handler.handle(command);
        
        assertThat(result.isError()).isTrue();
        assertThat(bookRepository.isAddCalled()).isFalse();
        assertThat(result.getError())
                .hasValueSatisfying(error -> assertThat(error.getMessage()).isEqualTo("Book already exists"));
        assertThat(domainEventPublisher.getLastSpecifiedDomainEvents()).isEmpty();
    }
    
    @Test
    public void handle_WhenBookSuccessfullyRegistered_ShouldReturnBookIdAndPublishDomainEvents() {
        RegisterBookCommand command = new RegisterBookCommand("Test", "Test", 0, Optional.empty(), Optional.empty());
        BookId generatedBookId = new BookId(1);
        bookRepository.setGeneratedBookId(generatedBookId);
        
        ErrorOr<BookId> result = handler.handle(command);
        
        assertThat(result.isError()).isFalse();
        assertThat(bookRepository.isAddCalled()).isTrue();
        assertThat(result.getResult()).hasValue(generatedBookId);
        assertThat(domainEventPublisher.getLastSpecifiedDomainEvents()).hasValueSatisfying(domainEvents -> {
            assertThat(domainEvents).satisfiesExactly(domainEvent1 -> {
                assertThat(domainEvent1)
                        .isInstanceOfSatisfying(BookCreatedDomainEvent.class, bookCreatedDomainEvent -> {
                            assertThat(bookCreatedDomainEvent.getBook().getId()).isEqualTo(generatedBookId);
                        });
            });
        });
    }
    
    @Before
    public void setUp() {
        bookRepository = new BookRepositoryMock();
        domainEventPublisher = new DomainEventPublisherMock();
        handler = new RegisterBookCommandHandler(bookRepository, domainEventPublisher);
    }
}
