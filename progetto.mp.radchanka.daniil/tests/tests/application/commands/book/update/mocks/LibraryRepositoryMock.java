package tests.application.commands.book.update.mocks;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import base.repository.Pagination;
import base.repository.SortCriteria;
import base.specification.Specification;
import domain.library.Library;
import domain.library.LibraryId;
import domain.library.LibraryRepository;

public class LibraryRepositoryMock implements LibraryRepository {
    private boolean exists;
    
    public void setExists(boolean exists) {
        this.exists = exists;
    }
    
    @Override
    public Optional<Library> getFirst(Specification<Library> specification) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public void add(Library entity) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public void update(Library entity) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public void addRange(Collection<Library> entity) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public List<Library> get(Specification<Library> specification) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public List<Library> get(Specification<Library> specification, Pagination pagination) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public List<Library> get(
            Specification<Library> specification,
            SortCriteria<Library> sortCriteria,
            Pagination pagination) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public List<Library> getAll() {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public void remove(LibraryId entityId) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public void removeRange(Collection<LibraryId> entityIds) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public void updateRange(Collection<Library> entity) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public boolean exists(Specification<Library> specification) {
        return exists;
    }
    
    @Override
    public LibraryId generateNewLibraryId() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
