package base.ddd;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

import base.utils.IteratorUtils;

public abstract class Entity<TId> {
	private Deque<DomainEvent> domainEvents;
	private TId id;

	public Entity(TId id) {
		this.id = id;
		this.domainEvents = new LinkedList<DomainEvent>();
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

	public Collection<DomainEvent> extractAllDomainEvents() {
		Collection<DomainEvent> result = IteratorUtils
				.toArrayList(domainEvents.descendingIterator());
		domainEvents.clear();
		return result;
	}

	public TId getId() {
		return this.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	protected void registerDomainEvent(DomainEvent domainEvent) {
		this.domainEvents.add(domainEvent);
	}
}
