package domain.user.events;

import base.ddd.DomainEvent;
import domain.common.Address;
import domain.user.User;

public class UserChangedAddressDomainEvent implements DomainEvent {
	private final User user;
	private final Address previousAddress;
	private final Address newAddress;

	public UserChangedAddressDomainEvent(User user, Address previousAddress,
			Address newAddress) {
		this.user = user;
		this.previousAddress = previousAddress;
		this.newAddress = newAddress;
	}

	public Address getNewAddress() {
		return newAddress;
	}

	public Address getPreviousAddress() {
		return previousAddress;
	}

	public User getUser() {
		return user;
	}
}
