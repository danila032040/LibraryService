package tests.application.commands.book.returnByUser.mocks;

import java.util.Collection;
import java.util.Optional;

import base.repository.AlreadyExistsException;
import base.repository.Pagination;
import base.repository.SortCriteria;
import base.specification.Specification;
import domain.user.User;
import domain.user.UserId;
import domain.user.UserRepository;

public class UserRepositoryMock implements UserRepository {
    private boolean exists;
    
    public void setExists(boolean exists) {
        this.exists = exists;
    }
    
    @Override
    public boolean exists(Specification<User> specification) {
        return exists;
    }
    
    @Override
    public void add(User entity) throws AlreadyExistsException {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public void addRange(Collection<User> entity) throws AlreadyExistsException {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public Collection<User> get(Specification<User> specification) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public Collection<User> get(Specification<User> specification, Pagination pagination) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public Collection<User> get(
            Specification<User> specification,
            SortCriteria<User> sortCriteria,
            Pagination pagination) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public Collection<User> getAll() {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public Optional<User> getFirst(Specification<User> specification) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public void remove(UserId entityId) {
        throw new UnsupportedOperationException("Not implemented");
        
    }
    
    @Override
    public void removeRange(Collection<UserId> entityIds) {
        throw new UnsupportedOperationException("Not implemented");
        
    }
    
    @Override
    public void update(User entity) {
        throw new UnsupportedOperationException("Not implemented");
        
    }
    
    @Override
    public void updateRange(Collection<User> entity) {
        throw new UnsupportedOperationException("Not implemented");
        
    }
    
    @Override
    public UserId generateNewUserId() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
