package application.queries.book.getById;

import java.util.Optional;

import base.mediator.request.Request;
import base.result.ErrorOr;
import domain.book.Book;

public class GetByIdQuery implements Request<ErrorOr<Optional<Book>>> {
    private final int bookId;
    
    public GetByIdQuery(int bookId) {
        this.bookId = bookId;
    }
    
    public int getBookId() {
        return bookId;
    }
}
