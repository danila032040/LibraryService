package domain.library;

import base.ddd.DomainEvent;
import domain.Address;

public class LibraryAddressChangedDomainEvent implements DomainEvent {

	private Address previousAddress;
	private Address currentAddress;

	public LibraryAddressChangedDomainEvent(Address previousAddress,
			Address currentAddress) {
		this.previousAddress = previousAddress;
		this.currentAddress = currentAddress;
	}

	public Address getPreviousAddress() {
		return previousAddress;
	}

	public Address getCurrentAddress() {
		return currentAddress;
	}

}
