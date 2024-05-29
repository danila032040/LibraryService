package base.mediator;

import base.mediator.notification.Notification;
import base.mediator.notification.NotificationDispatcher;
import base.mediator.notification.NotificationHandler;
import base.mediator.request.Request;
import base.mediator.request.RequestDispatcher;
import base.mediator.request.RequestHandler;
import base.mediator.request.exceptions.RequestHandlerAlreadyRegisteredException;

public class MediatorImpl implements Mediator {
	private final NotificationDispatcher notificationDispatcher;
	private final RequestDispatcher requestDispatcher;

	public MediatorImpl(RequestDispatcher requestDispatcher,
			NotificationDispatcher notificationDispatcher) {
		this.requestDispatcher = requestDispatcher;
		this.notificationDispatcher = notificationDispatcher;
	}

	public <TNotification extends Notification> NotificationDispatcher registerHandler(
			Class<TNotification> notificationType,
			NotificationHandler<TNotification> handler) {
		this.notificationDispatcher.registerHandler(notificationType, handler);
		return this;
	}

	public <TRequest extends Request<TResult>, TResult> RequestDispatcher registerHandler(
			Class<TRequest> requestType,
			RequestHandler<TRequest, TResult> handler)
			throws RequestHandlerAlreadyRegisteredException {
		this.requestDispatcher.registerHandler(requestType, handler);
		return this;
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
