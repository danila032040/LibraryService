package domain.book.specifications;

import java.util.Objects;

import base.specification.Specification;
import domain.book.Book;
import domain.book.BookId;

public class BookByIdSpecification implements Specification<Book> {
    private final BookId bookId;
    
    public BookByIdSpecification(BookId bookId) {
        Objects.requireNonNull(bookId);
        this.bookId = bookId;
    }
    
    @Override
    public boolean isSatisfiedBy(Book book) {
        Objects.requireNonNull(book);
        return book.getId().equals(bookId);
    }
}
