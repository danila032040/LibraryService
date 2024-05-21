package domain.library.specifications;

import base.specification.Specification;
import domain.library.Library;
import domain.library.LibraryId;

public class LibraryByIdSpecification implements Specification<Library> {

	private LibraryId libraryId;

	public LibraryByIdSpecification(LibraryId libraryId) {
		this.libraryId = libraryId;
	}

	@Override
	public boolean isSatisfiedBy(Library library) {
		return library.getId().equals(libraryId);
	}
}
