package base.mediator.notification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import base.mediator.notification.exceptions.NotificationHandlerNotFoundException;

public class NotificationDispatcherImpl implements NotificationDispatcher {
    private final Map<Class<? extends Notification>, List<NotificationHandler<? extends Notification>>> handlersByNotificationType;
    
    public NotificationDispatcherImpl() {
        handlersByNotificationType = new HashMap<>();
    }
    
    @Override
    public <TNotification extends Notification> NotificationDispatcher registerHandler(
            Class<TNotification> notificationType,
            NotificationHandler<TNotification> handler) {
        Objects.requireNonNull(notificationType);
        Objects.requireNonNull(handler);
        List<NotificationHandler<? extends Notification>> handlers = handlersByNotificationType
                .getOrDefault(notificationType, new ArrayList<>());
        handlers.add(handler);
        handlersByNotificationType.put(notificationType, handlers);
        return this;
    }
    
    @Override
    public <TNotification extends Notification> void sendNotification(TNotification notification) {
        Class<? extends Notification> notificationType = notification.getClass();
        
        Collection<? extends NotificationHandler<? extends Notification>> handlers = handlersByNotificationType
                .getOrDefault(notificationType, Collections.emptyList());
        
        if (handlers.isEmpty()) {
            throw new NotificationHandlerNotFoundException(notificationType);
        }
        
        handlers.forEach(handler -> handleNotification(notification, handler));
    }
    
    private <TNotification extends Notification> void handleNotification(
            TNotification notification,
            NotificationHandler<? extends Notification> handler) {
        @SuppressWarnings("unchecked")
        NotificationHandler<TNotification> castedHandler = (NotificationHandler<TNotification>) handler;
        castedHandler.handle(notification);
    }
}
