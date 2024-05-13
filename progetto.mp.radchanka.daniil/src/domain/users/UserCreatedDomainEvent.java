package domain.users;

import base.ddd.DomainEvent;

public class UserCreatedDomainEvent implements DomainEvent {
	private UserId userId;

	public UserCreatedDomainEvent(UserId userId) {

	}

	public UserId getUserId() {
		return userId;
	}
}
