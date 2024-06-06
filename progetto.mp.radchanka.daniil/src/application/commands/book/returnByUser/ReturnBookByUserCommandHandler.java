package application.commands.book.returnByUser;

import java.util.Optional;

import base.ddd.DomainEventPublisher;
import base.mediator.request.RequestHandler;
import base.result.ErrorOr;
import base.result.SuccessResult;
import domain.book.Book;
import domain.book.BookId;
import domain.book.BookRepository;
import domain.book.specifications.BookByIdSpecification;
import domain.user.UserId;
import domain.user.UserRepository;
import domain.user.specifications.UserByIdSpecification;

public class ReturnBookByUserCommandHandler implements RequestHandler<ReturnBookByUserCommand, ErrorOr<SuccessResult>> {
    
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final DomainEventPublisher domainEventPublisher;
    
    public ReturnBookByUserCommandHandler(
            BookRepository bookRepository,
            UserRepository userRepository,
            DomainEventPublisher domainEventPublisher) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.domainEventPublisher = domainEventPublisher;
    }
    
    @Override
    public ErrorOr<SuccessResult> handle(ReturnBookByUserCommand request) {
        try {
            BookId bookId = new BookId(request.getBookId());
            UserId userId = new UserId(request.getUserId());
            
            Optional<Book> optionalExistingBook = bookRepository.getFirst(new BookByIdSpecification(bookId));
            
            if (optionalExistingBook.isEmpty()) {
                return ErrorOr.fromErrorMessage("Book with specified id was not found");
            }
            
            if (userRepository.exists(new UserByIdSpecification(userId))) {
                return ErrorOr.fromErrorMessage("User with specified id was not found");
            }
            
            Book existingBook = optionalExistingBook.orElseThrow();
            
            existingBook.returnByUser(userId);
            
            bookRepository.update(existingBook);
            domainEventPublisher.publishDomainEvents(existingBook.extractAllDomainEvents());
            
            return ErrorOr.fromResult(SuccessResult.from("Successfully returned book"));
        } catch (Exception exc) {
            return ErrorOr.fromErrorMessage(exc.getMessage());
        }
    }
}
