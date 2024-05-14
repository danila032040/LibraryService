package domain.library;

import base.ddd.Entity;
import base.cloneable.Cloneable;
import domain.Address;
import domain.library.events.LibraryAddressChangedDomainEvent;
import domain.library.events.LibraryCreatedDomainEvent;

public class Library extends Entity<LibraryId> implements Cloneable<Library> {
	private Address address;

	private Library(LibraryId id, Address address) {
		super(id);
	}

	public static Library createNewLibrary(LibraryId id, Address address) {
		Library createdLibrary = new Library(id, address);
		createdLibrary.registerDomainEvent(new LibraryCreatedDomainEvent(id));
		return createdLibrary;
	}

	public void changeAddress(Address address) {
		Address previous = this.address;
		Address current = address;
		this.address = address;
		this
				.registerDomainEvent(
						new LibraryAddressChangedDomainEvent(
								previous,
								current));
	}

	@Override
	public Library createClone() {
		return new Library(
				this.getId().createClone(),
				this.getAddress().createClone());
	}

	public Address getAddress() {
		return address;
	}

}
