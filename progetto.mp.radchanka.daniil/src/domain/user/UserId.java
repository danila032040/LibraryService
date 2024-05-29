package domain.user;

import java.util.Iterator;
import java.util.List;

import base.cloneable.Cloneable;
import base.ddd.ValueObject;

public class UserId extends ValueObject implements Cloneable<UserId> {
	private final int id;

	public UserId(int id) {
		this.id = id;
	}

	@Override
	public UserId createClone() {
		return new UserId(this.getId());
	}

	@Override
	public Iterator<Object> getEqualityComponentsIterator() {
		return List.<Object>of(id).iterator();
	}

	public int getId() {
		return id;
	}
}
