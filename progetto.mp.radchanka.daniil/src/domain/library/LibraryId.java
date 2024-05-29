package domain.library;

import java.util.Iterator;
import java.util.List;

import base.cloneable.Cloneable;
import base.ddd.ValueObject;

public class LibraryId extends ValueObject implements Cloneable<LibraryId> {
	private final int id;

	public LibraryId(int id) {
		this.id = id;
	}

	@Override
	public LibraryId createClone() {
		return new LibraryId(this.getId());
	}
	@Override
	public Iterator<Object> getEqualityComponentsIterator() {
		return List.<Object>of(id).iterator();
	}

	public int getId() {
		return id;
	}
}
