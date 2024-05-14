package domain.library.events;

import base.ddd.DomainEvent;
import domain.library.LibraryId;

public class LibraryCreatedDomainEvent implements DomainEvent {
	private LibraryId libraryId;

	public LibraryCreatedDomainEvent(LibraryId libraryId) {

	}

	public LibraryId getLibraryId() {
		return libraryId;
	}
}
