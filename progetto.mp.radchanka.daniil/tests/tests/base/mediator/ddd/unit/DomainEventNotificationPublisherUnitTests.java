package tests.base.mediator.ddd.unit;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import base.ddd.DomainEvent;
import base.mediator.Notification;
import base.mediator.ddd.DomainEventNotification;
import base.mediator.ddd.DomainEventNotificationPublisher;
import tests.base.mediator.ddd.mocks.DomainEventMock;
import tests.base.mediator.ddd.mocks.NotificationDispatcherMock;

public class DomainEventNotificationPublisherUnitTests {

	private DomainEventNotificationPublisher domainEventPublisher;
	private NotificationDispatcherMock notificationDispatcher;

	@Test
	public void publishDomainEvents_ShouldSendNotificationOfTheNotificationDispatcherExactlyOnce() {
		DomainEventMock domainEvent = new DomainEventMock();
		domainEventPublisher
				.publishDomainEvents(Lists.newArrayList(domainEvent));

		int actualSendNotificationExecutionsCount = notificationDispatcher
				.getSendNotificationExecutionsCount();

		assertThat(actualSendNotificationExecutionsCount).isEqualTo(1);
	}

	@Test
	public void publishDomainEvents_ShouldWrapDomainEventsToGenericDomainEventNotificationAndSendThemByNotificationDispatcher() {
		DomainEventMock domainEvent = new DomainEventMock();
		domainEventPublisher
				.publishDomainEvents(Lists.newArrayList(domainEvent));

		Notification actualNotification = notificationDispatcher
				.getSubmitedNotifications()
				.get(0);
		DomainEvent actualDomainEvent = ((DomainEventNotification) actualNotification)
				.getDomainEvent();

		assertThat(actualNotification)
				.isExactlyInstanceOf(DomainEventNotification.class);
		assertThat(actualDomainEvent)
				.isExactlyInstanceOf(DomainEventMock.class)
				.isSameAs(domainEvent);
	}

	@Before
	public void setUp() {
		notificationDispatcher = new NotificationDispatcherMock();
		domainEventPublisher = new DomainEventNotificationPublisher(
				notificationDispatcher);
	}
}
