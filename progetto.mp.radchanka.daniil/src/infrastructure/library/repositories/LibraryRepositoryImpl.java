package infrastructure.library.repositories;

import java.util.Collection;
import java.util.function.Supplier;

import base.cloneable.CloneFactory;
import base.repository.InMemoryRepository;
import domain.library.Library;
import domain.library.LibraryId;
import domain.library.LibraryRepository;

public class LibraryRepositoryImpl
		extends
			InMemoryRepository<Library, LibraryId>
		implements
			LibraryRepository {

	public LibraryRepositoryImpl(Collection<Library> storage,
			Supplier<Collection<Library>> resultCollectionFactory,
			CloneFactory<Library> entityCloneFactory) {
		super(storage, resultCollectionFactory, entityCloneFactory);
	}

}
