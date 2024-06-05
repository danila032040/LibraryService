package base.mediator.notification;

public interface NotificationDispatcher {
    public <TNotification extends Notification> NotificationDispatcher registerHandler(
            Class<TNotification> notificationType,
            NotificationHandler<TNotification> handler);
    
    public <TNotification extends Notification> void sendNotification(TNotification notification);
}
