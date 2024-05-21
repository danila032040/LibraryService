package domain.user;

import java.util.Objects;
import base.cloneable.Cloneable;

public class UserId implements Cloneable<UserId> {
	private int id;

	public UserId(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof UserId))
			return false;
		UserId other = (UserId) obj;
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
	public UserId createClone() {
		return new UserId(this.getId());
	}
}
