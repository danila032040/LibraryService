package infrastructure.author.repositories;

import java.util.Collection;
import java.util.function.Supplier;

import base.cloneable.CloneFactory;
import base.repository.InMemoryRepository;
import domain.author.Author;
import domain.author.AuthorId;
import domain.author.AuthorRepository;

public class AuthorRepositoryImpl extends InMemoryRepository<Author, AuthorId>
		implements
			AuthorRepository {

	public AuthorRepositoryImpl(Collection<Author> storage,
			Supplier<Collection<Author>> resultCollectionFactory,
			CloneFactory<Author> entityCloneFactory) {
		super(storage, resultCollectionFactory, entityCloneFactory);
	}

}
