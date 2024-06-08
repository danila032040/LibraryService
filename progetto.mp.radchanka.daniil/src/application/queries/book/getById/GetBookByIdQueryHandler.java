package application.queries.book.getById;

import java.util.Optional;

import base.mediator.request.RequestHandler;
import base.result.ErrorOr;
import domain.book.Book;
import domain.book.BookId;
import domain.book.BookRepository;
import domain.book.specifications.BookByIdSpecification;

public class GetBookByIdQueryHandler implements RequestHandler<GetBookByIdQuery, ErrorOr<Optional<Book>>> {
    private final BookRepository bookRepository;
    
    public GetBookByIdQueryHandler(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    @Override
    public ErrorOr<Optional<Book>> handle(GetBookByIdQuery request) {
        return ErrorOr.fromResult(bookRepository.getFirst(new BookByIdSpecification(new BookId(request.getBookId()))));
    }
    
}
