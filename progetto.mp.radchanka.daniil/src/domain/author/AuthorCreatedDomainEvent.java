package domain.author;

import base.ddd.DomainEvent;

public class AuthorCreatedDomainEvent implements DomainEvent {
	private AuthorId authorId;

	public AuthorCreatedDomainEvent(AuthorId authorId) {
		this.authorId = authorId;
	}

	public AuthorId getAuthorId() {
		return authorId;
	}
}
