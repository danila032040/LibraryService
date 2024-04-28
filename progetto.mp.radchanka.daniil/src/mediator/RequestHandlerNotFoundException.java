package mediator;

public class RequestHandlerNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final Class<?> requestType;

    public RequestHandlerNotFoundException(Class<?> type) {
        super("No request handler found for type: " + type.getName());
        this.requestType = type;
    }

    public Class<?> getRequestType() {
        return requestType;
    }
}
