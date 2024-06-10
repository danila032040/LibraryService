package tests.application.commands.book.returnByUser.mocks;

import java.util.List;
import java.util.Optional;

import base.ddd.DomainEvent;
import base.ddd.DomainEventPublisher;

public class DomainEventPublisherMock implements DomainEventPublisher {
    private Optional<List<DomainEvent>> lastSpecifiedDomainEvents = Optional.empty();
    
    public Optional<List<DomainEvent>> getLastSpecifiedDomainEvents() {
        return lastSpecifiedDomainEvents;
    }
    
    @Override
    public void publishDomainEvents(List<DomainEvent> domainEvents) {
        lastSpecifiedDomainEvents = Optional.of(domainEvents);
    }
    
}
