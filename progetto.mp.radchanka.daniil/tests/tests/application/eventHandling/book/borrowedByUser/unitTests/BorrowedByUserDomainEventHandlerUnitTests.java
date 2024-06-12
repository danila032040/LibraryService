package tests.application.eventHandling.book.borrowedByUser.unitTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import application.eventHandling.book.borrowedByUser.BorrowedByUserDomainEventHandler;
import base.mediator.ddd.DomainEventNotification;
import domain.book.Book;
import domain.book.BookId;
import domain.book.events.BookBorrowedByUserDomainEvent;
import domain.common.Address;
import domain.user.User;
import domain.user.UserId;
import domain.user.exceptions.BookWasAlreadyBorrowedByTheUserDomainEvent;
import tests.application.eventHandling.book.borrowedByUser.mocks.LoggerMock;
import tests.application.eventHandling.book.borrowedByUser.mocks.UserRepositoryMock;

public class BorrowedByUserDomainEventHandlerUnitTests {
    private LoggerMock logger;
    private UserRepositoryMock userRepository;
    private BorrowedByUserDomainEventHandler handler;
    
    @Test
    public void handle_WhenBookWasAlreadyBorrowedByUser_ShouldDoNothing()
            throws BookWasAlreadyBorrowedByTheUserDomainEvent {
        Book book = Book.createNewBook(new BookId(1), "Test", "Test", 0, Optional.empty(), Optional.empty());
        User user = User.createNewUser(new UserId(1), "Test", "Test", new Address(), Optional.empty());
        userRepository.setUser(user);
        user.addBorrowedBook(book.getId());
        
        BookBorrowedByUserDomainEvent event = new BookBorrowedByUserDomainEvent(book, user.getId());
        DomainEventNotification<BookBorrowedByUserDomainEvent> notification = DomainEventNotification
                .fromDomainEvent(event);
        
        handler.handle(notification);
        
        assertThat(logger.getLastErrorMessage()).isEmpty();
        assertThat(userRepository.getLastSpecifiedUserInUpdate()).isEmpty();
    }
    
    @Test
    public void handle_WhenUserDoesNotExist_ShouldLogError() {
        Book book = Book.createNewBook(new BookId(1), "Test", "Test", 0, Optional.empty(), Optional.empty());
        UserId userId = new UserId(1);
        
        BookBorrowedByUserDomainEvent event = new BookBorrowedByUserDomainEvent(book, userId);
        DomainEventNotification<BookBorrowedByUserDomainEvent> notification = DomainEventNotification
                .fromDomainEvent(event);
        
        handler.handle(notification);
        
        assertThat(logger.getLastErrorMessage()).hasValue("Could not handle {0}: User({1}) were not found");
    }
    
    @Test
    public void handle_WhenUserDoesNotExist_ShouldNotUpdateUser() {
        Book book = Book.createNewBook(new BookId(1), "Test", "Test", 0, Optional.empty(), Optional.empty());
        UserId userId = new UserId(1);
        
        BookBorrowedByUserDomainEvent event = new BookBorrowedByUserDomainEvent(book, userId);
        DomainEventNotification<BookBorrowedByUserDomainEvent> notification = DomainEventNotification
                .fromDomainEvent(event);
        
        handler.handle(notification);
        
        assertThat(userRepository.getLastSpecifiedUserInUpdate()).isEmpty();
    }
    
    @Test
    public void handle_WhenUserExistsAndHasNotBorrowedBook_ShouldAddBookToUser() {
        Book book = Book.createNewBook(new BookId(1), "Test", "Test", 0, Optional.empty(), Optional.empty());
        User userFromRepository = User.createNewUser(new UserId(1), "Test", "Test", new Address(), Optional.empty());
        userRepository.setUser(userFromRepository);
        
        BookBorrowedByUserDomainEvent event = new BookBorrowedByUserDomainEvent(book, userFromRepository.getId());
        DomainEventNotification<BookBorrowedByUserDomainEvent> notification = DomainEventNotification
                .fromDomainEvent(event);
        
        handler.handle(notification);
        
        assertThat(logger.getLastErrorMessage()).isEmpty();
        assertThat(userRepository.getLastSpecifiedUserInUpdate()).hasValueSatisfying(user -> {
            assertThat(user.getBorrowedBookIds()).contains(book.getId());
        });
    }
    
    @Before
    public void setUp() {
        userRepository = new UserRepositoryMock();
        logger = new LoggerMock();
        handler = new BorrowedByUserDomainEventHandler(logger, userRepository);
    }
}
