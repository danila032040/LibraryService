package domain.library;

import java.util.Objects;
import base.cloneable.Cloneable;

public class LibraryId implements Cloneable<LibraryId> {
	private int id;

	public LibraryId(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof LibraryId))
			return false;
		LibraryId other = (LibraryId) obj;
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
	public LibraryId createClone() {
		return new LibraryId(this.getId());
	}
}
