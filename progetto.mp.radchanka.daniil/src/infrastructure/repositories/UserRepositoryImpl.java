package infrastructure.repositories;

import java.util.Collection;
import java.util.function.Supplier;

import base.cloneable.CloneFactory;
import base.repository.InMemoryRepository;
import domain.users.User;
import domain.users.UserId;
import domain.users.UserRepository;

public class UserRepositoryImpl extends InMemoryRepository<User, UserId>
		implements
			UserRepository {

	public UserRepositoryImpl(Collection<User> storage,
			Supplier<Collection<User>> resultCollectionFactory,
			CloneFactory<User> entityCloneFactory) {
		super(storage, resultCollectionFactory, entityCloneFactory);
	}

}
