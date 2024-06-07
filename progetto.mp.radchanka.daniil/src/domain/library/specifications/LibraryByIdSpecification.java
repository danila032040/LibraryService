package domain.library.specifications;

import java.util.Objects;

import base.specification.Specification;
import domain.library.Library;
import domain.library.LibraryId;

public class LibraryByIdSpecification implements Specification<Library> {
    private final LibraryId libraryId;
    
    public LibraryByIdSpecification(LibraryId libraryId) {
        Objects.requireNonNull(libraryId);
        this.libraryId = libraryId;
    }
    
    @Override
    public boolean isSatisfiedBy(Library library) {
        Objects.requireNonNull(library);
        return library.getId().equals(libraryId);
    }
}
