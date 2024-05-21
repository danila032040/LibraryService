package domain.library.events;

import base.ddd.DomainEvent;
import domain.common.Address;
import domain.library.Library;

public class LibraryAddressChangedDomainEvent implements DomainEvent {

	private Address previousAddress;
	private Address currentAddress;
	private Library library;

	public LibraryAddressChangedDomainEvent(Library library,
			Address previousAddress, Address currentAddress) {
		this.library = library;
		this.previousAddress = previousAddress;
		this.currentAddress = currentAddress;
	}

	public Library getLibrary() {
		return library;
	}

	public Address getPreviousAddress() {
		return previousAddress;
	}

	public Address getCurrentAddress() {
		return currentAddress;
	}

}
