package domain.author;

import java.util.Iterator;
import java.util.List;

import base.cloneable.Cloneable;
import base.ddd.ValueObject;

public class AuthorId extends ValueObject
		implements
			Cloneable<AuthorId>,
			Comparable<AuthorId> {
	private final int id;

	public AuthorId(int id) {
		this.id = id;
	}

	@Override
	public AuthorId createClone() {
		return new AuthorId(this.getId());
	}

	@Override
	public Iterator<Object> getEqualityComponentsIterator() {
		return List.<Object>of(id).iterator();
	}

	public int getId() {
		return id;
	}

	@Override
	public int compareTo(AuthorId other) {
		return Integer.compare(this.getId(), other.getId());
	}
}