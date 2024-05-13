package domain.library;

import base.ddd.DomainEvent;

public class LibraryCreatedDomainEvent implements DomainEvent {
	private LibraryId libraryId;

	public LibraryCreatedDomainEvent(LibraryId libraryId) {

	}

	public LibraryId getLibraryId() {
		return libraryId;
	}
}
