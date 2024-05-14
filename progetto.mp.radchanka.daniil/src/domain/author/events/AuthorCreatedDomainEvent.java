package domain.author.events;

import base.ddd.DomainEvent;
import domain.author.AuthorId;

public class AuthorCreatedDomainEvent implements DomainEvent {
	private AuthorId authorId;

	public AuthorCreatedDomainEvent(AuthorId authorId) {
		this.authorId = authorId;
	}

	public AuthorId getAuthorId() {
		return authorId;
	}
}
