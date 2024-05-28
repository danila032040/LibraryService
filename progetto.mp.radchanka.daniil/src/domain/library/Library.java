package domain.library;

import base.ddd.Entity;

import java.util.Objects;

import base.cloneable.Cloneable;
import domain.common.Address;
import domain.library.events.LibraryAddressChangedDomainEvent;
import domain.library.events.LibraryCreatedDomainEvent;

public class Library extends Entity<LibraryId> implements Cloneable<Library> {
	private Address address;

	private Library(LibraryId id, Address address) {
		super(Objects.requireNonNull(id));
		this.address = Objects.requireNonNull(address);
	}

	public static Library createNewLibrary(LibraryId id, Address address) {
		Library createdLibrary = new Library(id, address);
		createdLibrary
				.registerDomainEvent(
						new LibraryCreatedDomainEvent(createdLibrary));
		return createdLibrary;
	}

	public void changeAddress(Address address) {
		Address previous = this.address;
		Address current = address;
		this.address = address;
		this
				.registerDomainEvent(
						new LibraryAddressChangedDomainEvent(
								this,
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
