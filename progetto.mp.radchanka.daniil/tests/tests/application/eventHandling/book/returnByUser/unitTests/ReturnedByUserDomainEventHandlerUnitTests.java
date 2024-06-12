package tests.application.eventHandling.book.returnByUser.unitTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import application.eventHandling.book.returnByUser.ReturnedByUserDomainEventHandler;
import base.mediator.ddd.DomainEventNotification;
import domain.book.Book;
import domain.book.BookId;
import domain.book.events.BookReturnedByUserDomainEvent;
import domain.common.Address;
import domain.user.User;
import domain.user.UserId;
import domain.user.exceptions.BookWasAlreadyBorrowedByTheUserDomainEvent;
import domain.user.exceptions.BookWasNotBorrowedByTheUserDomainEvent;
import tests.application.eventHandling.book.borrowedByUser.mocks.LoggerMock;
import tests.application.eventHandling.book.borrowedByUser.mocks.UserRepositoryMock;

public class ReturnedByUserDomainEventHandlerUnitTests {
    private LoggerMock logger;
    private UserRepositoryMock userRepository;
    private ReturnedByUserDomainEventHandler handler;
    
    @Test
    public void handle_WhenBookWasNotBorrowedByUser_ShouldDoNothing() throws BookWasNotBorrowedByTheUserDomainEvent {
        Book book = Book.createNewBook(new BookId(1), "Test", "Test", 0, Optional.empty(), Optional.empty());
        User user = User.createNewUser(new UserId(1), "Test", "Test", new Address(), Optional.empty());
        userRepository.setUser(user);
        
        BookReturnedByUserDomainEvent event = new BookReturnedByUserDomainEvent(book, user.getId());
        DomainEventNotification<BookReturnedByUserDomainEvent> notification = DomainEventNotification
                .fromDomainEvent(event);
        
        handler.handle(notification);
        
        assertThat(logger.getLastErrorMessage()).isEmpty();
        assertThat(userRepository.getLastSpecifiedUserInUpdate()).isEmpty();
    }
    
    @Test
    public void handle_WhenUserDoesNotExist_ShouldLogError() {
        Book book = Book.createNewBook(new BookId(1), "Test", "Test", 0, Optional.empty(), Optional.empty());
        UserId userId = new UserId(1);
        
        BookReturnedByUserDomainEvent event = new BookReturnedByUserDomainEvent(book, userId);
        DomainEventNotification<BookReturnedByUserDomainEvent> notification = DomainEventNotification
                .fromDomainEvent(event);
        
        handler.handle(notification);
        
        assertThat(logger.getLastErrorMessage()).hasValue("Could not handle {0}: User({1}) were not found");
        assertThat(userRepository.getLastSpecifiedUserInUpdate()).isEmpty();
    }
    
    @Test
    public void handle_WhenUserDoesNotExist_ShouldNotUpdateUser() {
        Book book = Book.createNewBook(new BookId(1), "Test", "Test", 0, Optional.empty(), Optional.empty());
        UserId userId = new UserId(1);
        
        BookReturnedByUserDomainEvent event = new BookReturnedByUserDomainEvent(book, userId);
        DomainEventNotification<BookReturnedByUserDomainEvent> notification = DomainEventNotification
                .fromDomainEvent(event);
        
        handler.handle(notification);
        
        assertThat(userRepository.getLastSpecifiedUserInUpdate()).isEmpty();
    }
    
    @Test
    public void handle_WhenUserExistsAndHasBorrowedBook_ShouldUpdateUser()
            throws BookWasAlreadyBorrowedByTheUserDomainEvent {
        Book book = Book.createNewBook(new BookId(1), "Test", "Test", 0, Optional.empty(), Optional.empty());
        User userFromRepository = User.createNewUser(new UserId(1), "Test", "Test", new Address(), Optional.empty());
        userFromRepository.addBorrowedBook(book.getId());
        userRepository.setUser(userFromRepository);
        
        BookReturnedByUserDomainEvent event = new BookReturnedByUserDomainEvent(book, userFromRepository.getId());
        DomainEventNotification<BookReturnedByUserDomainEvent> notification = DomainEventNotification
                .fromDomainEvent(event);
        
        handler.handle(notification);
        
        assertThat(logger.getLastErrorMessage()).isEmpty();
        assertThat(userRepository.getLastSpecifiedUserInUpdate()).hasValueSatisfying(user -> {
            assertThat(user.getBorrowedBookIds()).doesNotContain(book.getId());
        });
    }
    
    @Before
    public void setUp() {
        userRepository = new UserRepositoryMock();
        logger = new LoggerMock();
        handler = new ReturnedByUserDomainEventHandler(logger, userRepository);
    }
}
