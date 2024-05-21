package domain.user.events;

import base.ddd.DomainEvent;
import domain.user.User;

public class UserChangedPhoneNumberDomainEvent implements DomainEvent {

	private User user;
	private String previousPhoneNumber;
	private String newPhoneNumber;

	public UserChangedPhoneNumberDomainEvent(User user, String previousPhoneNumber,
			String newPhoneNumber) {
		this.user = user;
		this.previousPhoneNumber = previousPhoneNumber;
		this.newPhoneNumber = newPhoneNumber;
	}

	public User getUser() {
		return user;
	}

	public String getPreviousPhoneNumber() {
		return previousPhoneNumber;
	}

	public String getNewPhoneNumber() {
		return newPhoneNumber;
	}

}
