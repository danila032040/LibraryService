package tests.base.ddd.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import base.ddd.DomainEvent;
import tests.base.ddd.mocks.DomainEventMock;
import tests.base.ddd.mocks.EntityMock;

public class EntityUnitTests {
    
    @Test
    public void equals_WhenAtLeastOneIdIsNull_ShouldNotThrowAnyException() {
        EntityMock entity1 = new EntityMock();
        EntityMock entity2 = new EntityMock(1);
        
        ThrowingCallable actual = () -> entity1.equals(entity2);
        
        assertThatNoException().isThrownBy(actual);
    }
    
    @Test
    public void equals_WhenComparedWithTheDifferentClassObject_ShouldBeFalse() {
        EntityMock entity = new EntityMock(1);
        Integer otherInstance = 5;
        
        @SuppressWarnings("unlikely-arg-type")
        boolean actual = entity.equals(otherInstance);
        
        assertThat(actual).isFalse();
    }
    
    @Test
    public void equals_WhenComparingWithTheSameInstance_ShouldBeTrue() {
        EntityMock entity = new EntityMock(1);
        
        boolean actual = entity.equals(entity);
        
        assertThat(actual).isTrue();
    }
    
    @Test
    public void equals_WhenIdsAreEqual_ShouldBeTrue() {
        EntityMock entity1 = new EntityMock(5);
        EntityMock entity2 = new EntityMock(5);
        
        boolean actual = entity1.equals(entity2);
        
        assertThat(actual).isTrue();
    }
    
    @Test
    public void equals_WhenIdsAreNotEqual_ShouldBeFalse() {
        EntityMock entity1 = new EntityMock(5);
        EntityMock entity2 = new EntityMock(2);
        
        boolean actual = entity1.equals(entity2);
        
        assertThat(actual).isFalse();
    }
    
    @Test
    public void extractAllDomainEvents_Twice_ShouldReturnEmptyCollectionOnTheSecondExtraction() {
        EntityMock entity = new EntityMock(5);
        List<DomainEventMock> domainEventsToRegister = Stream
                .generate(DomainEventMock::new)
                .limit(5)
                .collect(Collectors.toList());
        
        domainEventsToRegister.forEach(entity::registerDomainEvent);
        entity.extractAllDomainEvents();
        Collection<DomainEvent> registeredDomainEvents = entity.extractAllDomainEvents();
        
        assertThat(registeredDomainEvents).isEmpty();
    }
    
    @Test
    public void extractAllDomainEvents_WhenNoDomainEventWereRegisteredBefore_ShouldReturnEmptyCollection() {
        EntityMock entity = new EntityMock(5);
        
        Collection<DomainEvent> registeredDomainEvents = entity.extractAllDomainEvents();
        
        assertThat(registeredDomainEvents).isEmpty();
    }
    
    @Test
    public void hashCode_WhenIdIsNull_ShouldNotThrowAnyException() {
        EntityMock entity = new EntityMock();
        
        ThrowingCallable actual = () -> entity.hashCode();
        
        assertThatNoException().isThrownBy(actual);
    }
    
    @Test
    public void hashCode_WhenIdsAreEqual_HashCodesShouldBeEqualForBothEntities() {
        EntityMock entity1 = new EntityMock(5);
        EntityMock entity2 = new EntityMock(5);
        
        int actual1 = entity1.hashCode();
        int actual2 = entity2.hashCode();
        
        assertThat(actual1).isEqualTo(actual2);
    }
    
    @Test
    public void hashCode_WhenIdsAreNotEqual_HashCodesShouldBeNotEqualForBothEntities() {
        EntityMock mock1 = new EntityMock(5);
        EntityMock mock2 = new EntityMock(2);
        
        int actual1 = mock1.hashCode();
        int actual2 = mock2.hashCode();
        
        assertThat(actual1).isNotEqualTo(actual2);
    }
    
    @Test
    public void registerDomainEvent_ExtractedEventsShouldContainOnlyTheRegisteredEvent() {
        EntityMock entity = new EntityMock(5);
        DomainEventMock expectedDomainEvent = new DomainEventMock();
        
        entity.registerDomainEvent(expectedDomainEvent);
        Collection<DomainEvent> registeredDomainEvents = entity.extractAllDomainEvents();
        
        assertThat(registeredDomainEvents).containsOnly(expectedDomainEvent);
    }
    
    @Test
    public void registerDomainEvent_WhenRegisterMultipleEvents_ExtractedEventsShouldContainRegisteredEventInRevertedOrderOfTheRegistration() {
        EntityMock entity = new EntityMock(5);
        List<DomainEventMock> domainEventsToRegister = Stream
                .generate(DomainEventMock::new)
                .limit(5)
                .collect(Collectors.toList());
        List<DomainEvent> expectedDomainEvents = new ArrayList<>(domainEventsToRegister);
        Collections.reverse(expectedDomainEvents);
        
        domainEventsToRegister.forEach(entity::registerDomainEvent);
        Collection<DomainEvent> registeredDomainEvents = entity.extractAllDomainEvents();
        
        assertThat(registeredDomainEvents).containsExactly(expectedDomainEvents.toArray(new DomainEvent[0]));
    }
}
