package mediator;

public interface NotificationDispatcher {
	public <TNotification extends Notification> void registerHandler(
			Class<TNotification> notificationType,
			NotificationHandler<TNotification> handler);

	public <TNotification extends Notification> void sendNotification(
			TNotification notification);
}
