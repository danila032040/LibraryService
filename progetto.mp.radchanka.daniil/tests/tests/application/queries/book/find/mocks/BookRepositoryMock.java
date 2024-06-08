package tests.application.queries.book.find.mocks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import base.repository.AlreadyExistsException;
import base.repository.Pagination;
import base.repository.SortCriteria;
import base.specification.Specification;
import domain.book.Book;
import domain.book.BookId;
import domain.book.BookRepository;

public class BookRepositoryMock implements BookRepository {
    
    private final List<Book> books = new ArrayList<>();
    
    @Override
    public Collection<Book> get(
            Specification<Book> specification,
            SortCriteria<Book> sortCriteria,
            Pagination pagination) {
        return books
                .stream()
                .filter(specification::isSatisfiedBy)
                .sorted(sortCriteria.getSortComparator())
                .skip(pagination.getPageIndex() * pagination.getPageSize())
                .limit(pagination.getPageSize())
                .collect(Collectors.toList());
    }
    
    public void addBook(Book book) {
        books.add(book);
    }
    
    public void addBooks(Collection<Book> books) {
        this.books.addAll(books);
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
    public Collection<Book> getAll() {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public Optional<Book> getFirst(Specification<Book> specification) {
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
    public void update(Book entity) {
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