package domain.user.events;

import base.ddd.DomainEvent;
import domain.user.User;

public class UserSpecifiedPhoneNumberDomainEvent implements DomainEvent {

	private User user;
	private String specifiedPhoneNumber;

	public UserSpecifiedPhoneNumberDomainEvent(User user,
			String specifiedPhoneNumber) {
		this.user = user;
		this.specifiedPhoneNumber = specifiedPhoneNumber;
	}

	public User getUser() {
		return user;
	}

	public String getSpecifiedPhoneNumber() {
		return specifiedPhoneNumber;
	}
}
