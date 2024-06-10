package tests.application.commands.book.update.mocks;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import base.repository.Pagination;
import base.repository.SortCriteria;
import base.specification.Specification;
import domain.author.Author;
import domain.author.AuthorId;
import domain.author.AuthorRepository;

public class AuthorRepositoryMock implements AuthorRepository {
    private boolean exists;
    
    public void setExists(boolean exists) {
        this.exists = exists;
    }
    
    @Override
    public Optional<Author> getFirst(Specification<Author> specification) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public void add(Author entity) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public void update(Author entity) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public void addRange(Collection<Author> entity) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public List<Author> get(Specification<Author> specification) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public List<Author> get(Specification<Author> specification, Pagination pagination) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public List<Author> get(
            Specification<Author> specification,
            SortCriteria<Author> sortCriteria,
            Pagination pagination) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public List<Author> getAll() {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public void remove(AuthorId entityId) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public void removeRange(Collection<AuthorId> entityIds) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public void updateRange(Collection<Author> entity) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public boolean exists(Specification<Author> specification) {
        return exists;
    }
    
    @Override
    public AuthorId generateNewAuthorId() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
