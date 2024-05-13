package tests.base.mediator.mocks;

import base.mediator.Notification;
import base.mediator.NotificationDispatcher;
import base.mediator.NotificationHandler;

public class NotificationDispatcherMock implements NotificationDispatcher{
	private int registerHandlerExecutionsCount;
	private int sendNotificationExecutionsCount;
	
	@Override
	public <TNotification extends Notification> NotificationDispatcher registerHandler(
			Class<TNotification> notificationType, NotificationHandler<TNotification> handler) {
		++registerHandlerExecutionsCount;
		return this;
	}

	@Override
	public <TNotification extends Notification> void sendNotification(TNotification notification) {
		++sendNotificationExecutionsCount;
	}

	public int getRegisterHandlerExecutionsCount() {
		return registerHandlerExecutionsCount;
	}

	public int getSendNotificationExecutionsCount() {
		return sendNotificationExecutionsCount;
	}

}
