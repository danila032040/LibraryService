package domain.book;

import java.util.Iterator;
import java.util.List;

import base.cloneable.Cloneable;
import base.ddd.ValueObject;

public class BookId extends ValueObject implements Cloneable<BookId> {
	private final int id;

	public BookId(int id) {
		this.id = id;
	}

	@Override
	public BookId createClone() {
		return new BookId(this.getId());
	}

	@Override
	public Iterator<Object> getEqualityComponentsIterator() {
		return List.<Object>of(id).iterator();
	}

	public int getId() {
		return id;
	}
}
