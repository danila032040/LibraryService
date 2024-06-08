package tests.application.commands.book.register.mocks;

import java.util.Collection;
import java.util.Optional;

import base.repository.AlreadyExistsException;
import base.repository.Pagination;
import base.repository.SortCriteria;
import base.specification.Specification;
import domain.book.Book;
import domain.book.BookId;
import domain.book.BookRepository;

public class BookRepositoryMock implements BookRepository {
    private Optional<Book> book = Optional.empty();
    private BookId generatedBookId;
    private boolean throwAlreadyExistsException;
    
    public void setBook(Optional<Book> book) {
        this.book = book;
    }
    
    public void setGeneratedBookId(BookId generatedBookId) {
        this.generatedBookId = generatedBookId;
    }
    
    public void setThrowAlreadyExistsException(boolean throwAlreadyExistsException) {
        this.throwAlreadyExistsException = throwAlreadyExistsException;
    }
    
    @Override
    public Optional<Book> getFirst(Specification<Book> specification) {
        return book;
    }
    
    @Override
    public void update(Book book) {
        // No-op
    }
    
    @Override
    public void add(Book entity) throws AlreadyExistsException {
        if (throwAlreadyExistsException) {
            throw new AlreadyExistsException("Book already exists");
        }
        this.book = Optional.of(entity);
    }
    
    @Override
    public void addRange(Collection<Book> entity) throws AlreadyExistsException {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public Collection<Book> get(Specification<Book> specification) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public Collection<Book> get(Specification<Book> specification, Pagination pagination) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public Collection<Book> get(
            Specification<Book> specification,
            SortCriteria<Book> sortCriteria,
            Pagination pagination) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public Collection<Book> getAll() {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public void remove(BookId entityId) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public void removeRange(Collection<BookId> entityIds) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public void updateRange(Collection<Book> entity) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public BookId generateNewBookId() {
        return generatedBookId;
    }
}
