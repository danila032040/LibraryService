package tests.base.ddd.mocks;

import base.ddd.Entity;

public class EntityMock extends Entity<EntityIdMock> {
    public EntityMock() {
        super(null);
    }
    
    public EntityMock(int id) {
        super(new EntityIdMock(id));
    }
    
    public void registerDomainEvent(DomainEventMock domainEvent) {
        super.registerDomainEvent(domainEvent);
    }
}
