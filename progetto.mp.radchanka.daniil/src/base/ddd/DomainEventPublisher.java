package base.ddd;

import java.util.Collection;

public interface DomainEventPublisher {
    public void publishDomainEvents(Collection<DomainEvent> domainEvents);
}
