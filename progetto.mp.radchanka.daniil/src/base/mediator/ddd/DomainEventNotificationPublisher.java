package base.mediator.ddd;

import java.util.Collection;

import base.ddd.DomainEvent;
import base.ddd.DomainEventPublisher;
import base.mediator.notification.NotificationDispatcher;

public class DomainEventNotificationPublisher implements DomainEventPublisher {

	private final NotificationDispatcher notificationDispatcher;

	public DomainEventNotificationPublisher(
			NotificationDispatcher notificationDispatcher) {
		this.notificationDispatcher = notificationDispatcher;
	}
	@Override
	public void publishDomainEvents(Collection<DomainEvent> domainEvents) {
		domainEvents
				.stream()
				.map(domainEvent -> domainEvent.getClass().cast(domainEvent))
				.map(DomainEventNotification::fromDomainEvent)
				.forEach(notificationDispatcher::sendNotification);
	}

}
