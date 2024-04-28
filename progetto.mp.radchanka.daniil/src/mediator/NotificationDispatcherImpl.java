package mediator;

import java.util.HashMap;
import java.util.Map;

public class NotificationDispatcherImpl implements NotificationDispatcher {
	private final Map<Class<? extends Notification>, NotificationHandler<? extends Notification>> handlers;
	
	public NotificationDispatcherImpl() {
		handlers = new HashMap<>();
	}

	public <TNotification extends Notification> void registerHandler(
			Class<TNotification> notificationType,
			NotificationHandler<TNotification> handler) {
		handlers.put(notificationType, handler);
	}

	public <TNotification extends Notification> void sendNotification(
			TNotification notification) {
		Class<? extends Notification> notificationType = notification
				.getClass();

		NotificationHandler<? extends Notification> handler = handlers
				.get(notificationType);

		if (handler == null)
			throw new NotificationHandlerNotFoundException(notificationType);

		handleNotification(notification, handler);
	}

	private <TNotification extends Notification> void handleNotification(
			TNotification notification,
			NotificationHandler<? extends Notification> handler) {
		@SuppressWarnings("unchecked")
		NotificationHandler<TNotification> castedHandler = (NotificationHandler<TNotification>) handler;
		castedHandler.handle(notification);
	}
}
