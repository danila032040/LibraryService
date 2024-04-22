package domainDrivenDesign;

import java.util.Objects;

public abstract class Entity<TId> {
	private TId id;

	public TId getId() {
		return this.id;
	}

	public void setId(TId id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Entity<?>))
			return false;
		Entity<?> other = (Entity<?>) obj;
		return Objects.equals(id, other.id);
	}

}
