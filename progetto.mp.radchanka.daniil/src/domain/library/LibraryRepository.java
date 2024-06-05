package domain.library;

import base.repository.Repository;
import base.specification.Specification;

public interface LibraryRepository extends Repository<Library, LibraryId> {
    public LibraryId generateNewLibraryId();
    
    public boolean exists(Specification<Library> specification);
}
