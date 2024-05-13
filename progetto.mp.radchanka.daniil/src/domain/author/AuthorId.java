package domain.author;

import java.util.Objects;

import base.cloneable.Cloneable;

public class AuthorId implements Cloneable<AuthorId> {
	private int id;

	public AuthorId(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof AuthorId))
			return false;
		AuthorId other = (AuthorId) obj;
		return id == other.id;
	}

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public AuthorId createClone() {
		return new AuthorId(this.getId());
	}
}