package base.ddd;

import java.util.List;

public interface DomainEventPublisher {
    public void publishDomainEvents(List<DomainEvent> domainEvents);
}
