package base.mediator;

import base.mediator.notification.NotificationDispatcher;
import base.mediator.request.RequestDispatcher;

public interface Mediator extends RequestDispatcher, NotificationDispatcher {
}
