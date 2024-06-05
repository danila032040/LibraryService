package application.commands.book.update;

import java.util.Objects;
import java.util.Optional;

import application.commands.common.address.AddressCommandData;
import base.ddd.DomainEventPublisher;
import base.mediator.request.RequestHandler;
import base.result.ErrorOr;
import base.result.Success;
import base.result.ValidationResult;
import base.utils.Mapper;
import base.utils.Validator;
import domain.author.AuthorId;
import domain.book.Book;
import domain.book.BookId;
import domain.book.BookRepository;
import domain.book.specifications.BookByIdSpecification;
import domain.common.Address;
import domain.library.LibraryId;

public class UpdateBookCommandHandler implements RequestHandler<UpdateBookCommand, ErrorOr<Success>> {
    private final Validator<UpdateBookCommand> validator;
    private final BookRepository bookRepository;
    private final DomainEventPublisher domainEventPublisher;
    
    public UpdateBookCommandHandler(
            Validator<UpdateBookCommand> validator,
            BookRepository bookRepository,
            DomainEventPublisher domainEventPublisher,
            Mapper<AddressCommandData, Address> addressMapper) {
        this.validator = validator;
        this.bookRepository = bookRepository;
        this.domainEventPublisher = domainEventPublisher;
    }
    
    @Override
    public ErrorOr<Success> handle(UpdateBookCommand request) {
        try {
            ValidationResult validationResult = validator.validate(request);
            if (!validationResult.isValid()) {
                return ErrorOr.fromErrorMessage(validationResult.getErrors().get(0).getErrorMessage());
            }
            
            Optional<AuthorId> newAuthorId = request.getAuthorId().map(AuthorId::new);
            Optional<LibraryId> newLibraryId = request.getLibraryId().map(LibraryId::new);
            
            Optional<Book> optionalExistingBook = bookRepository
                    .getFirst(new BookByIdSpecification(new BookId(request.getBookId())));
            
            if (optionalExistingBook.isEmpty()) {
                return ErrorOr.fromErrorMessage("Book with specified id was not found");
            }
            
            Book existingBook = optionalExistingBook.orElseThrow();
            
            boolean hasInformationToUpdate = false;
            
            if (!Objects.equals(existingBook.getGenre(), request.getGenre())) {
                existingBook.setGenre(request.getGenre());
                hasInformationToUpdate = true;
            }
            if (!Objects.equals(existingBook.getName(), request.getName())) {
                existingBook.setName(request.getName());
                hasInformationToUpdate = true;
            }
            if (!Objects.equals(existingBook.getPublicationYear(), request.getPublicationYear())) {
                existingBook.setPublicationYear(request.getPublicationYear());
                hasInformationToUpdate = true;
            }
            
            if (!isAuthorIdNewerForTheBook(newAuthorId, existingBook)) {
                newAuthorId.ifPresent(existingBook::setAuthor);
                hasInformationToUpdate = true;
            }
            
            if (!isLibraryIdNewerForTheBook(newLibraryId, existingBook)) {
                newLibraryId.ifPresent(existingBook::setLibrary);
                hasInformationToUpdate = true;
            }
            
            if (!hasInformationToUpdate) {
                return ErrorOr
                        .fromResult(Success.from("Book already contains provided information. Update is not needed"));
            }
            
            bookRepository.update(existingBook);
            
            domainEventPublisher.publishDomainEvents(existingBook.extractAllDomainEvents());
            
            return ErrorOr.fromResult(Success.from("Successfully updated book information"));
        } catch (Exception exc) {
            return ErrorOr.fromErrorMessage(exc.getMessage());
        }
    }
    
    private Boolean isAuthorIdNewerForTheBook(Optional<AuthorId> newAuthorId, Book existingBook) {
        return newAuthorId
                .map(
                        authorId -> existingBook.getAuthorId().isEmpty()
                                || !Objects.equals(existingBook.getAuthorId().get(), authorId))
                .orElse(false);
    }
    
    private Boolean isLibraryIdNewerForTheBook(Optional<LibraryId> newLibraryId, Book existingBook) {
        return newLibraryId
                .map(
                        authorId -> existingBook.getLibraryId().isEmpty()
                                || !Objects.equals(existingBook.getLibraryId().get(), authorId))
                .orElse(false);
    }
    
}
