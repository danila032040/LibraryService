package infrastructure.user.repositories;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

import base.cloneable.CloneFactory;
import base.repository.InMemoryRepository;
import domain.user.User;
import domain.user.UserId;
import domain.user.UserRepository;

public class UserRepositoryImpl extends InMemoryRepository<User, UserId>
		implements
			UserRepository {
	private Optional<Integer> lastGeneratedId;

	public UserRepositoryImpl(Collection<User> storage,
			Supplier<Collection<User>> resultCollectionFactory,
			CloneFactory<User> entityCloneFactory) {
		super(storage, resultCollectionFactory, entityCloneFactory);
		lastGeneratedId = storage
				.stream()
				.map(User::getId)
				.map(UserId::getId)
				.max(Integer::compare);
	}

	@Override
	public UserId generateNewUserId() {
		return new UserId(
				lastGeneratedId.map(x -> x + 1).or(() -> Optional.of(0)).get());
	}
}
