package domain.library;

import base.repository.Repository;

public interface LibraryRepository extends Repository<Library, LibraryId> {
    public LibraryId generateNewLibraryId();
}
