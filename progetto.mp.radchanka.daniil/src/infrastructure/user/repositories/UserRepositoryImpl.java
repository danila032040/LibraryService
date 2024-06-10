package infrastructure.user.repositories;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import base.cloneable.CloneFactory;
import base.repository.InMemoryRepository;
import base.specification.Specification;
import domain.user.User;
import domain.user.UserId;
import domain.user.UserRepository;

public class UserRepositoryImpl extends InMemoryRepository<User, UserId> implements UserRepository {
    private int idSeed;
    
    public UserRepositoryImpl(
            Collection<User> storage,
            Supplier<List<User>> resultCollectionFactory,
            CloneFactory<User> entityCloneFactory) {
        super(storage, resultCollectionFactory, entityCloneFactory);
        idSeed = storage.stream().map(User::getId).map(UserId::getId).max(Integer::compare).map(x -> x + 1).orElse(0);
    }
    
    @Override
    public UserId generateNewUserId() {
        return new UserId(idSeed++);
    }
    
    @Override
    public boolean exists(Specification<User> specification) {
        return this.getFirst(specification).isPresent();
    }
}
