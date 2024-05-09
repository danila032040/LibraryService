package base.mediator;

public interface NotificationHandler<TNotification extends Notification>{
	void handle(TNotification notification);
}
