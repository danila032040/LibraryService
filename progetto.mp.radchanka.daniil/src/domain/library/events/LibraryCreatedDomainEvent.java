package domain.library.events;

import base.ddd.DomainEvent;
import domain.library.Library;

public class LibraryCreatedDomainEvent implements DomainEvent {
	private Library library;

	public LibraryCreatedDomainEvent(Library library) {
		this.library = library;

	}

	public Library getLibrary() {
		return library;
	}

}
