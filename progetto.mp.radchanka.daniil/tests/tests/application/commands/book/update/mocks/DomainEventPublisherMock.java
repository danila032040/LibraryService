package tests.application.commands.book.update.mocks;

import java.util.Collection;
import java.util.Optional;

import base.ddd.DomainEvent;
import base.ddd.DomainEventPublisher;

public class DomainEventPublisherMock implements DomainEventPublisher {
    private Optional<Collection<DomainEvent>> lastSpecifiedDomainEvents = Optional.empty();
    
    public Optional<Collection<DomainEvent>> getLastSpecifiedDomainEvents() {
        return lastSpecifiedDomainEvents;
    }
    
    @Override
    public void publishDomainEvents(Collection<DomainEvent> domainEvents) {
        lastSpecifiedDomainEvents = Optional.of(domainEvents);
    }
}
