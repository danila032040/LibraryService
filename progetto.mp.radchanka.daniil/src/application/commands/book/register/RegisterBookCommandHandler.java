package application.commands.book.register;

import base.ddd.DomainEventPublisher;
import base.mediator.request.RequestHandler;
import base.result.ErrorOr;
import domain.author.AuthorId;
import domain.book.Book;
import domain.book.BookId;
import domain.book.BookRepository;
import domain.library.LibraryId;

public class RegisterBookCommandHandler implements RequestHandler<RegisterBookCommand, ErrorOr<BookId>> {
    
    private final BookRepository bookRepository;
    private final DomainEventPublisher domainEventPublisher;
    
    public RegisterBookCommandHandler(BookRepository bookRepository, DomainEventPublisher domainEventPublisher) {
        this.bookRepository = bookRepository;
        this.domainEventPublisher = domainEventPublisher;
    }
    
    @Override
    public ErrorOr<BookId> handle(RegisterBookCommand request) {
        try {
            
            Book bookToRegister = Book
                    .createNewBook(
                            bookRepository.generateNewBookId(),
                            request.getName(),
                            request.getGenre(),
                            request.getPublicationYear(),
                            request.getAuthorId().map(AuthorId::new),
                            request.getLibraryId().map(LibraryId::new));
            
            bookRepository.add(bookToRegister);
            
            domainEventPublisher.publishDomainEvents(bookToRegister.extractAllDomainEvents());
            
            return ErrorOr.fromResult(bookToRegister.getId());
        } catch (Exception exc) {
            return ErrorOr.fromErrorMessage(exc.getMessage());
        }
    }
    
}
