package base.mediator.notification;

public interface NotificationHandler<TNotification extends Notification>{
	void handle(TNotification notification);
}
