package domain.user.events;

import base.ddd.DomainEvent;
import domain.common.Address;
import domain.user.User;

public class UserChangedAddressDomainEvent implements DomainEvent {

	private User user;
	private Address previousAddress;
	private Address newAddress;

	public UserChangedAddressDomainEvent(User user, Address previousAddress,
			Address newAddress) {
		this.user = user;
		this.previousAddress = previousAddress;
		this.newAddress = newAddress;
	}

	public User getUser() {
		return user;
	}

	public Address getPreviousAddress() {
		return previousAddress;
	}

	public Address getNewAddress() {
		return newAddress;
	}
}
