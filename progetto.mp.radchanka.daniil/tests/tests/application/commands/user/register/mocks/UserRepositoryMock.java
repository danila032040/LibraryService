package tests.application.commands.user.register.mocks;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import base.repository.AlreadyExistsException;
import base.repository.Pagination;
import base.repository.SortCriteria;
import base.specification.Specification;
import domain.user.User;
import domain.user.UserId;
import domain.user.UserRepository;

public class UserRepositoryMock implements UserRepository {
    private Optional<User> user = Optional.empty();
    private UserId generatedUserId;
    private boolean throwAlreadyExistsException;
    private boolean isAddCalled;
    
    @Override
    public void add(User entity) throws AlreadyExistsException {
        if (throwAlreadyExistsException) {
            throw new AlreadyExistsException("User already exists");
        }
        this.isAddCalled = true;
    }
    
    @Override
    public void addRange(Collection<User> entity) throws AlreadyExistsException {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public boolean exists(Specification<User> specification) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public UserId generateNewUserId() {
        return generatedUserId;
    }
    
    @Override
    public List<User> get(Specification<User> specification) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public List<User> get(Specification<User> specification, Pagination pagination) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public List<User> get(Specification<User> specification, SortCriteria<User> sortCriteria, Pagination pagination) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public List<User> getAll() {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public Optional<User> getFirst(Specification<User> specification) {
        return user;
    }
    
    public boolean isAddCalled() {
        return isAddCalled;
    }
    
    @Override
    public void remove(UserId entityId) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    @Override
    public void removeRange(Collection<UserId> entityIds) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public void setGeneratedUserId(UserId generatedUserId) {
        this.generatedUserId = generatedUserId;
    }
    
    public void setThrowAlreadyExistsException(boolean throwAlreadyExistsException) {
        this.throwAlreadyExistsException = throwAlreadyExistsException;
    }
    
    public void setUser(Optional<User> user) {
        this.user = user;
    }
    
    @Override
    public void update(User user) {
        // No-op
    }
    
    @Override
    public void updateRange(Collection<User> entity) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
