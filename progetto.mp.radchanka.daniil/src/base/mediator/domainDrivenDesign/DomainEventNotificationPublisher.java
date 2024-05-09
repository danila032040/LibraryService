package base.mediator.domainDrivenDesign;

import java.util.Collection;

import base.ddd.DomainEvent;
import base.ddd.DomainEventPublisher;
import base.mediator.NotificationDispatcher;

public class DomainEventNotificationPublisher implements DomainEventPublisher {

	private NotificationDispatcher notificationDispatcher;

	public DomainEventNotificationPublisher(
			NotificationDispatcher notificationDispatcher) {
		this.notificationDispatcher = notificationDispatcher;
	}
	@Override
	public void publishDomainEvents(Collection<DomainEvent> domainEvents) {
		domainEvents
				.stream()
				.map(DomainEventNotification::fromDomainEvent)
				.forEach(notificationDispatcher::sendNotification);
	}

}
