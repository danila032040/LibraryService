package mediator;

public class NotificationHandlerNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final Class<?> notificationType;

    public NotificationHandlerNotFoundException(Class<?> notificationType) {
        super("No notification handler found for type: " + notificationType.getName());
        this.notificationType = notificationType;
    }

    public Class<?> getNotificationType() {
        return notificationType;
    }
}