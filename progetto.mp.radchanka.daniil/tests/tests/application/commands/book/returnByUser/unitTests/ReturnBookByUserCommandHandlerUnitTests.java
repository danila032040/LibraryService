package tests.application.commands.book.returnByUser.unitTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import application.commands.book.returnByUser.ReturnBookByUserCommand;
import application.commands.book.returnByUser.ReturnBookByUserCommandHandler;
import base.result.ErrorOr;
import base.result.ErrorResult;
import base.result.SuccessResult;
import domain.book.Book;
import domain.book.BookId;
import domain.book.exceptions.BookIsAlreadyBorrowedByAnotherUserDomainException;
import domain.user.UserId;
import tests.application.commands.book.returnByUser.mocks.BookRepositoryMock;
import tests.application.commands.book.returnByUser.mocks.DomainEventPublisherMock;
import tests.application.commands.book.returnByUser.mocks.UserRepositoryMock;

public class ReturnBookByUserCommandHandlerUnitTests {
    
    private BookRepositoryMock bookRepository;
    private UserRepositoryMock userRepository;
    private DomainEventPublisherMock domainEventPublisher;
    private ReturnBookByUserCommandHandler handler;
    
    @Before
    public void setUp() {
        bookRepository = new BookRepositoryMock();
        userRepository = new UserRepositoryMock();
        domainEventPublisher = new DomainEventPublisherMock();
        handler = new ReturnBookByUserCommandHandler(bookRepository, userRepository, domainEventPublisher);
    }
    
    @Test
    public void handle_WhenBookNotFound_ShouldReturnErrorMessage() {
        ReturnBookByUserCommand command = new ReturnBookByUserCommand(1, 1);
        bookRepository.setBook(Optional.empty());
        
        ErrorOr<SuccessResult> result = handler.handle(command);
        
        assertThat(result.isError()).isTrue();
        assertThat(result.getError()).map(ErrorResult::getMessage).hasValue("Book with specified id was not found");
        assertThat(domainEventPublisher.getLastSpecifiedDomainEvents()).isEmpty();
    }
    
    @Test
    public void handle_WhenUserNotFound_ShouldReturnErrorMessage() {
        ReturnBookByUserCommand command = new ReturnBookByUserCommand(1, 1);
        Book existingBook = createBookWithoutDomainEvents(0);
        bookRepository.setBook(Optional.of(existingBook));
        userRepository.setExists(false);
        
        ErrorOr<SuccessResult> result = handler.handle(command);
        
        assertThat(result.isError()).isTrue();
        assertThat(result.getError()).map(ErrorResult::getMessage).hasValue("User with specified id was not found");
        assertThat(domainEventPublisher.getLastSpecifiedDomainEvents()).isEmpty();
    }
    
    @Test
    public void handle_WhenUserTriedToReturnBookOfAnotherUser_ShouldReturnErrorMessage()
            throws BookIsAlreadyBorrowedByAnotherUserDomainException {
        ReturnBookByUserCommand command = new ReturnBookByUserCommand(1, 2);
        Book existingBook = createBookWithoutDomainEvents(0);
        existingBook.borrowByUser(new UserId(1));
        bookRepository.setBook(Optional.of(existingBook));
        userRepository.setExists(true);
        
        ErrorOr<SuccessResult> result = handler.handle(command);
        
        assertThat(result.isError()).isTrue();
        assertThat(result.getError())
                .map(ErrorResult::getMessage)
                .hasValue("User tried to return the book of another user");
        assertThat(domainEventPublisher.getLastSpecifiedDomainEvents()).isEmpty();
    }
    
    @Test
    public void handle_WhenUserTriedToReturnBookThatWereNotBorrowed_ShouldReturnErrorMessage() {
        ReturnBookByUserCommand command = new ReturnBookByUserCommand(1, 1);
        Book existingBook = createBookWithoutDomainEvents(0);
        bookRepository.setBook(Optional.of(existingBook));
        userRepository.setExists(true);
        
        ErrorOr<SuccessResult> result = handler.handle(command);
        
        assertThat(result.isError()).isTrue();
        assertThat(result.getError())
                .map(ErrorResult::getMessage)
                .hasValue("User tried to return the book that were not borrowed previously");
        assertThat(domainEventPublisher.getLastSpecifiedDomainEvents()).isEmpty();
    }
    
    @Test
    public void handle_WhenBookIsSuccessfullyReturned_ShouldReturnSuccessResult()
            throws BookIsAlreadyBorrowedByAnotherUserDomainException {
        ReturnBookByUserCommand command = new ReturnBookByUserCommand(1, 1);
        Book existingBook = createBookWithoutDomainEvents(0);
        existingBook.borrowByUser(new UserId(1));
        bookRepository.setBook(Optional.of(existingBook));
        userRepository.setExists(true);
        
        ErrorOr<SuccessResult> result = handler.handle(command);
        
        assertThat(result.isError()).isFalse();
        assertThat(result.getResult()).map(SuccessResult::getMessage).hasValue("Successfully returned book");
        assertThat(bookRepository.isUpdateCalled()).isTrue();
        assertThat(domainEventPublisher.getLastSpecifiedDomainEvents()).isNotEmpty();
    }
    
    private Book createBookWithoutDomainEvents(int id) {
        Book createdBook = Book
                .createNewBook(new BookId(id), "Book Name", "Genre", 0, Optional.empty(), Optional.empty());
        createdBook.extractAllDomainEvents();
        return createdBook;
    }
}
