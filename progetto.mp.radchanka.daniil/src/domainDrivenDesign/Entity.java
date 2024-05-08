package domainDrivenDesign;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public abstract class Entity<TId> {
	private Collection<DomainEvent> domainEvents;
	private TId id;
	
	public Entity(TId id) {
		this.id = id;
		this.domainEvents = new ArrayList<DomainEvent>();
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

	public Collection<DomainEvent> extractAllDomainEvents(){
		var result = List.copyOf(domainEvents);
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
	
	public void setId(TId id) {
		this.id = id;
	}
	
	protected void registerDomainEvent(DomainEvent domainEvent) {
		this.domainEvents.add(domainEvent);
	}
}
