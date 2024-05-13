package base.mediator.ddd;

import base.ddd.DomainEvent;
import base.mediator.Notification;

public class DomainEventNotification<TDomainEvent extends DomainEvent> implements Notification {
	public static <TDomainEvent extends DomainEvent> DomainEventNotification<TDomainEvent> fromDomainEvent(
			TDomainEvent domainEvent) {
		return new DomainEventNotification<TDomainEvent>(domainEvent);
	}

	private TDomainEvent domainEvent;

	private DomainEventNotification(TDomainEvent domainEvent) {
		this.domainEvent = domainEvent;
	}

	public TDomainEvent getDomainEvent() {
		return domainEvent;
	}
}
