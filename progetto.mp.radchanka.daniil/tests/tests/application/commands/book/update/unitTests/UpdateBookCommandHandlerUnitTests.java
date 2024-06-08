package tests.application.commands.book.update.unitTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import application.commands.book.update.UpdateBookCommand;
import application.commands.book.update.UpdateBookCommandHandler;
import application.commands.common.address.AddressCommandData;
import application.commands.common.address.AddressCommandDataMapper;
import base.result.ErrorOr;
import base.result.ErrorResult;
import base.result.SuccessResult;
import base.utils.Mapper;
import domain.author.AuthorId;
import domain.book.Book;
import domain.book.BookId;
import domain.common.Address;
import domain.library.LibraryId;
import tests.application.commands.book.update.mocks.AuthorRepositoryMock;
import tests.application.commands.book.update.mocks.BookRepositoryMock;
import tests.application.commands.book.update.mocks.DomainEventPublisherMock;
import tests.application.commands.book.update.mocks.LibraryRepositoryMock;

public class UpdateBookCommandHandlerUnitTests {
    
    private BookRepositoryMock bookRepository;
    private LibraryRepositoryMock libraryRepository;
    private AuthorRepositoryMock authorRepository;
    private DomainEventPublisherMock domainEventPublisher;
    private UpdateBookCommandHandler handler;
    
    @Before
    public void setUp() {
        bookRepository = new BookRepositoryMock();
        libraryRepository = new LibraryRepositoryMock();
        authorRepository = new AuthorRepositoryMock();
        domainEventPublisher = new DomainEventPublisherMock();
        Mapper<AddressCommandData, Address> addressMapper = new AddressCommandDataMapper();
        handler = new UpdateBookCommandHandler(
                bookRepository,
                libraryRepository,
                authorRepository,
                domainEventPublisher,
                addressMapper);
    }
    
    @Test
    public void handle_WhenBookNotFound_ShouldReturnErrorMessage() {
        UpdateBookCommand command = new UpdateBookCommand(
                1,
                "New Name",
                "New Genre",
                0,
                Optional.of(1),
                Optional.of(1));
        bookRepository.setBook(Optional.empty());
        
        ErrorOr<SuccessResult> result = handler.handle(command);
        
        assertThat(result.isError()).isTrue();
        assertThat(result.getError()).map(ErrorResult::getMessage).hasValue("Book with specified id was not found");
        assertThat(domainEventPublisher.getLastSpecifiedDomainEvents()).isEmpty();
    }
    
    @Test
    public void handle_WhenLibraryNotFound_ShouldReturnErrorMessage() {
        UpdateBookCommand command = new UpdateBookCommand(
                1,
                "New Name",
                "New Genre",
                0,
                Optional.of(1),
                Optional.of(1));
        bookRepository.setBook(Optional.of(createBookWithoutDomainEvents(1, "Old Name", "Old Genre", 0)));
        libraryRepository.setExists(false);
        
        ErrorOr<SuccessResult> result = handler.handle(command);
        
        assertThat(result.isError()).isTrue();
        assertThat(result.getError()).map(ErrorResult::getMessage).hasValue("Library with specified id was not found");
        assertThat(domainEventPublisher.getLastSpecifiedDomainEvents()).isEmpty();
    }
    
    @Test
    public void handle_WhenAuthorNotFound_ShouldReturnErrorMessage() {
        UpdateBookCommand command = new UpdateBookCommand(
                1,
                "New Name",
                "New Genre",
                0,
                Optional.of(1),
                Optional.of(1));
        bookRepository.setBook(Optional.of(createBookWithoutDomainEvents(1, "Old Name", "Old Genre", 0)));
        libraryRepository.setExists(true);
        authorRepository.setExists(false);
        
        ErrorOr<SuccessResult> result = handler.handle(command);
        
        assertThat(result.isError()).isTrue();
        assertThat(result.getError()).map(ErrorResult::getMessage).hasValue("Author with specified id was not found");
        assertThat(domainEventPublisher.getLastSpecifiedDomainEvents()).isEmpty();
    }
    
    @Test
    public void handle_WhenNoUpdateNeeded_ShouldReturnSuccessResultWithoutUpdate() {
        Book existingBook = createBookWithoutDomainEvents(1, "Old Name", "Old Genre", 0);
        UpdateBookCommand command = new UpdateBookCommand(
                1,
                "Old Name",
                "Old Genre",
                0,
                Optional.empty(),
                Optional.empty());
        bookRepository.setBook(Optional.of(existingBook));
        libraryRepository.setExists(true);
        authorRepository.setExists(true);
        
        ErrorOr<SuccessResult> result = handler.handle(command);
        
        assertThat(result.isError()).isFalse();
        assertThat(result.getResult())
                .map(SuccessResult::getMessage)
                .hasValue("Book already contains provided information. Update is not needed");
        assertThat(bookRepository.isUpdateCalled()).isFalse();
        assertThat(domainEventPublisher.getLastSpecifiedDomainEvents()).isEmpty();
    }
    
    @Test
    public void handle_WhenUpdateIsNeeded_ShouldUpdateBookAndPublishDomainEvents() {
        Book existingBook = createBookWithoutDomainEvents(1, "Old Name", "Old Genre", 0, 1, 1);
        UpdateBookCommand command = new UpdateBookCommand(
                1,
                "New Name",
                "New Genre",
                0,
                Optional.of(1),
                Optional.of(1));
        bookRepository.setBook(Optional.of(existingBook));
        libraryRepository.setExists(true);
        authorRepository.setExists(true);
        
        ErrorOr<SuccessResult> result = handler.handle(command);
        
        assertThat(result.isError()).isFalse();
        assertThat(result.getResult()).map(SuccessResult::getMessage).hasValue("Successfully updated book information");
        assertThat(bookRepository.isUpdateCalled()).isTrue();
    }
    
    private Book createBookWithoutDomainEvents(
            int id,
            String name,
            String genre,
            int publicationYear,
            int authorId,
            int libraryId) {
        Book createdBook = Book
                .createNewBook(
                        new BookId(id),
                        name,
                        genre,
                        publicationYear,
                        Optional.of(new AuthorId(authorId)),
                        Optional.of(new LibraryId(libraryId)));
        createdBook.extractAllDomainEvents();
        return createdBook;
    }
    
    private Book createBookWithoutDomainEvents(int id, String name, String genre, int publicationYear) {
        Book createdBook = Book
                .createNewBook(new BookId(id), name, genre, publicationYear, Optional.empty(), Optional.empty());
        createdBook.extractAllDomainEvents();
        return createdBook;
    }
}
