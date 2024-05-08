package mediator.domainDrivenDesign;

import java.util.Collection;

import domainDrivenDesign.DomainEvent;
import domainDrivenDesign.DomainEventPublisher;
import mediator.NotificationDispatcher;

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
				.map(DomainEventNotification::of)
				.forEach(notificationDispatcher::sendNotification);
	}

}
