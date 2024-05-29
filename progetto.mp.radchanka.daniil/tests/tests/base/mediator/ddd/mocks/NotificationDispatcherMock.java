package tests.base.mediator.ddd.mocks;

import java.util.ArrayList;
import java.util.List;

import base.mediator.notification.Notification;
import base.mediator.notification.NotificationDispatcher;
import base.mediator.notification.NotificationHandler;

public class NotificationDispatcherMock implements NotificationDispatcher {
	private int registerHandlerExecutionsCount;
	private int sendNotificationExecutionsCount;
	private final List<Notification> sumbetedNotifications = new ArrayList<>();

	public int getRegisterHandlerExecutionsCount() {
		return registerHandlerExecutionsCount;
	}

	public int getSendNotificationExecutionsCount() {
		return sendNotificationExecutionsCount;
	}

	public List<Notification> getSubmitedNotifications() {
		return sumbetedNotifications;
	}

	@Override
	public <TNotification extends Notification> NotificationDispatcher registerHandler(
			Class<TNotification> notificationType,
			NotificationHandler<TNotification> handler) {
		++registerHandlerExecutionsCount;
		return this;
	}

	@Override
	public <TNotification extends Notification> void sendNotification(
			TNotification notification) {
		++sendNotificationExecutionsCount;
		sumbetedNotifications.add(notification);
	}

}
