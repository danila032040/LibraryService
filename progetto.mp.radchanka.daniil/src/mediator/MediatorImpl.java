package mediator;

public class MediatorImpl implements Mediator {
	private final NotificationDispatcher notificationDispatcher;
	private final RequestDispatcher requestDispatcher;

	public MediatorImpl(RequestDispatcher requestDispatcher,
			NotificationDispatcher notificationDispatcher) {
		this.requestDispatcher = requestDispatcher;
		this.notificationDispatcher = notificationDispatcher;
	}

	public <TNotification extends Notification> void registerHandler(
			Class<TNotification> notificationType,
			NotificationHandler<TNotification> handler) {
		this.notificationDispatcher.registerHandler(notificationType, handler);
	}

	public <TRequest extends Request<TResult>, TResult> void registerHandler(
			Class<TRequest> requestType,
			RequestHandler<TRequest, TResult> handler) {
		this.requestDispatcher.registerHandler(requestType, handler);
	}

	public <TNotification extends Notification> void sendNotification(
			TNotification notification) {
		this.notificationDispatcher.sendNotification(notification);
	}

	public <TRequest extends Request<TResult>, TResult> TResult sendRequest(
			TRequest request) {
		return this.requestDispatcher.sendRequest(request);
	}

}
