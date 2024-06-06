package infrastructure.library.repositories;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

import base.cloneable.CloneFactory;
import base.repository.InMemoryRepository;
import base.specification.Specification;
import domain.library.Library;
import domain.library.LibraryId;
import domain.library.LibraryRepository;

public class LibraryRepositoryImpl extends InMemoryRepository<Library, LibraryId> implements LibraryRepository {
    private Optional<Integer> lastGeneratedId;
    
    public LibraryRepositoryImpl(
            Collection<Library> storage,
            Supplier<Collection<Library>> resultCollectionFactory,
            CloneFactory<Library> entityCloneFactory) {
        super(storage, resultCollectionFactory, entityCloneFactory);
        lastGeneratedId = storage.stream().map(Library::getId).map(LibraryId::getId).max(Integer::compare);
    }
    
    @Override
    public LibraryId generateNewLibraryId() {
        return new LibraryId(lastGeneratedId.map(x -> x + 1).or(() -> Optional.of(0)).get());
    }
    
    @Override
    public boolean exists(Specification<Library> specification) {
        return this.getFirst(specification).isPresent();
    }
}
