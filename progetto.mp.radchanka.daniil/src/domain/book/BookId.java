package domain.book;

import java.util.Objects;
import base.cloneable.Cloneable;

public class BookId implements Cloneable<BookId> {
	private int id;

	public BookId(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof BookId))
			return false;
		BookId other = (BookId) obj;
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
	public BookId createClone() {
		return new BookId(this.getId());
	}
}
