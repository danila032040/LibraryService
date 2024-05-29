package domain.user.events;

import base.ddd.DomainEvent;
import domain.user.User;

public class UserCreatedDomainEvent implements DomainEvent {
	private final User user;

	public UserCreatedDomainEvent(User user) {
		this.user = user;

	}

	public User getUser() {
		return user;
	}
}
