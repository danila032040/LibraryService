package tests.base.mediator.ddd.unit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import base.ddd.DomainEvent;
import base.mediator.ddd.DomainEventNotification;
import tests.base.mediator.ddd.mocks.DomainEventMock;

public class DomainEventNotificationUnitTests {

	@Test
	public void fromDomainEvent_ShouldCreateDomainEventNotificationWithSpecifiedDomainEvent() {
		DomainEventMock expectedDomainEvent = new DomainEventMock();
		DomainEventNotification<DomainEventMock> domainEventNotification = DomainEventNotification
				.fromDomainEvent(expectedDomainEvent);

		DomainEvent actual = domainEventNotification.getDomainEvent();

		assertThat(actual).isSameAs(expectedDomainEvent);
	}
}
