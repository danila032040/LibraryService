package domain.users.events;

import base.ddd.DomainEvent;
import domain.users.UserId;

public class UserCreatedDomainEvent implements DomainEvent {
	private UserId userId;

	public UserCreatedDomainEvent(UserId userId) {

	}

	public UserId getUserId() {
		return userId;
	}
}
