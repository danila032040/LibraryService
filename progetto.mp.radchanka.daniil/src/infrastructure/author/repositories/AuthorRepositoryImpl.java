package infrastructure.author.repositories;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

import base.cloneable.CloneFactory;
import base.repository.InMemoryRepository;
import domain.author.Author;
import domain.author.AuthorId;
import domain.author.AuthorRepository;

public class AuthorRepositoryImpl extends InMemoryRepository<Author, AuthorId>
		implements
			AuthorRepository {
	private Optional<Integer> lastGeneratedId;

	public AuthorRepositoryImpl(Collection<Author> storage,
			Supplier<Collection<Author>> resultCollectionFactory,
			CloneFactory<Author> entityCloneFactory) {
		super(storage, resultCollectionFactory, entityCloneFactory);
		lastGeneratedId = storage
				.stream()
				.map(Author::getId)
				.map(AuthorId::getId)
				.max(Integer::compare);
	}

	@Override
	public AuthorId generateNewAuthorId() {
		return new AuthorId(
				lastGeneratedId.map(x -> x + 1).or(() -> Optional.of(0)).get());
	}
}
