package infrastructure.library.repositories;

import java.util.Collection;
import java.util.function.Supplier;

import base.cloneable.CloneFactory;
import base.repository.InMemoryRepository;
import base.specification.Specification;
import domain.library.Library;
import domain.library.LibraryId;
import domain.library.LibraryRepository;

public class LibraryRepositoryImpl extends InMemoryRepository<Library, LibraryId> implements LibraryRepository {
    private int idSeed;
    
    public LibraryRepositoryImpl(
            Collection<Library> storage,
            Supplier<Collection<Library>> resultCollectionFactory,
            CloneFactory<Library> entityCloneFactory) {
        super(storage, resultCollectionFactory, entityCloneFactory);
        idSeed = storage
                .stream()
                .map(Library::getId)
                .map(LibraryId::getId)
                .max(Integer::compare)
                .map(x -> x + 1)
                .orElse(0);
    }
    
    @Override
    public LibraryId generateNewLibraryId() {
        return new LibraryId(idSeed++);
    }
    
    @Override
    public boolean exists(Specification<Library> specification) {
        return this.getFirst(specification).isPresent();
    }
}
