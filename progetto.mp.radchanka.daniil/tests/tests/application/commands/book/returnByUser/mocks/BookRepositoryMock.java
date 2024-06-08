package tests.application.commands.book.returnByUser.mocks;

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
    private boolean isUpdateCalled = false;
    
    public void setBook(Optional<Book> book) {
        this.book = book;
    }
    
    public boolean isUpdateCalled() {
        return isUpdateCalled;
    }
    
    @Override
    public Optional<Book> getFirst(Specification<Book> specification) {
        return book;
    }
    
    @Override
    public void update(Book book) {
        isUpdateCalled = true;
    }
    
    @Override
    public void add(Book entity) throws AlreadyExistsException {
        throw new UnsupportedOperationException("Not implemented");
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
        throw new UnsupportedOperationException("Not implemented");
    }
}
