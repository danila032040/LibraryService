package tests.application.commands.book.borrowByUser;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import application.commands.book.borrowByUser.BorrowBookByUserCommand;
import application.commands.book.borrowByUser.BorrowBookByUserCommandHandler;
import application.commands.common.address.AddressCommandData;
import application.commands.common.address.AddressCommandDataMapper;
import base.result.ErrorOr;
import base.result.ErrorResult;
import base.result.SuccessResult;
import base.utils.Mapper;
import domain.book.Book;
import domain.book.BookId;
import domain.book.events.BookBorrowedByUserDomainEvent;
import domain.book.exceptions.BookIsAlreadyBorrowedByAnotherUserDomainException;
import domain.common.Address;
import domain.user.UserId;
import tests.application.commands.book.borrowByUser.mocks.BookRepositoryMock;
import tests.application.commands.book.borrowByUser.mocks.DomainEventPublisherMock;
import tests.application.commands.book.borrowByUser.mocks.UserRepositoryMock;

public class BorrowBookByUserCommandHandlerUnitTests {
    
    private BookRepositoryMock bookRepository;
    private UserRepositoryMock userRepository;
    private DomainEventPublisherMock domainEventPublisher;
    private BorrowBookByUserCommandHandler handler;
    private Mapper<AddressCommandData, Address> addressMapper;
    
    @Before
    public void setUp() {
        bookRepository = new BookRepositoryMock();
        userRepository = new UserRepositoryMock();
        domainEventPublisher = new DomainEventPublisherMock();
        addressMapper = new AddressCommandDataMapper();
        handler = new BorrowBookByUserCommandHandler(
                bookRepository,
                userRepository,
                domainEventPublisher,
                addressMapper);
    }
    
    @Test
    public void handle_WhenBookNotFound_ShouldReturnErrorMessage() {
        BorrowBookByUserCommand command = new BorrowBookByUserCommand(0, 0);
        bookRepository.setBook(Optional.empty());
        
        ErrorOr<SuccessResult> result = handler.handle(command);
        
        assertThat(result.isError()).isTrue();
        assertThat(result.getError()).map(ErrorResult::getMessage).hasValue("Book with specified id was not found");
        assertThat(domainEventPublisher.getLastSpecifiedDomainEvents()).isEmpty();
    }
    
    @Test
    public void handle_WhenUserNotFound_ShouldReturnErrorMessage() {
        BorrowBookByUserCommand command = new BorrowBookByUserCommand(0, 0);
        Book book = createBookWithoutDomainEvents(0);
        bookRepository.setBook(Optional.of(book));
        userRepository.setExists(false);
        
        ErrorOr<SuccessResult> result = handler.handle(command);
        
        assertThat(result.isError()).isTrue();
        assertThat(result.getError()).map(ErrorResult::getMessage).hasValue("User with specified id was not found");
        assertThat(domainEventPublisher.getLastSpecifiedDomainEvents()).isEmpty();
    }
    
    @Test
    public void handle_WhenBookAlreadyBorrowed_ShouldReturnErrorMessage()
            throws BookIsAlreadyBorrowedByAnotherUserDomainException {
        BorrowBookByUserCommand command = new BorrowBookByUserCommand(0, 0);
        Book book = createBookWithoutDomainEvents(0);
        book.borrowByUser(new UserId(1));
        bookRepository.setBook(Optional.of(book));
        userRepository.setExists(true);
        
        ErrorOr<SuccessResult> result = handler.handle(command);
        
        assertThat(result.isError()).isTrue();
        assertThat(result.getError()).map(ErrorResult::getMessage).hasValue("Book were already borrowed");
        assertThat(domainEventPublisher.getLastSpecifiedDomainEvents()).isEmpty();
    }
    
    @Test
    public void handle_WhenSuccessful_ShouldReturnSuccessMessage() {
        BorrowBookByUserCommand command = new BorrowBookByUserCommand(0, 0);
        Book book = createBookWithoutDomainEvents(0);
        bookRepository.setBook(Optional.of(book));
        userRepository.setExists(true);
        
        ErrorOr<SuccessResult> result = handler.handle(command);
        
        assertThat(result.isError()).isFalse();
        assertThat(result.getResult()).map(SuccessResult::getMessage).hasValue("Successfully borrowed book");
        assertThat(book.getBorrowedByUserId()).isEqualTo(Optional.of(new UserId(0)));
        assertThat(domainEventPublisher.getLastSpecifiedDomainEvents()).hasValueSatisfying(domainEvents -> {
            assertThat(domainEvents).satisfiesExactly(domainEvent1 -> {
                assertThat(domainEvent1)
                        .isInstanceOfSatisfying(BookBorrowedByUserDomainEvent.class, bookBorrowedByUserDomainEvent -> {
                            assertThat(bookBorrowedByUserDomainEvent.getBorrowedBook()).isSameAs(book);
                            assertThat(bookBorrowedByUserDomainEvent.getUserIdThatHadBorrowedTheBook().getId())
                                    .isEqualTo(0);
                        });
            });
        });
    }
    
    private Book createBookWithoutDomainEvents(int id) {
        Book createdBook = Book
                .createNewBook(new BookId(id), "Book Name", "Genre", 0, Optional.empty(), Optional.empty());
        createdBook.extractAllDomainEvents();
        return createdBook;
    }
}
