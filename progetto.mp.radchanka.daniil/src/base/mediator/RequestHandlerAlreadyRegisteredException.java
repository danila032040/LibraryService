package base.mediator;

public class RequestHandlerAlreadyRegisteredException extends Exception {
	private static final long serialVersionUID = 1L;
	private final Class<?> requestType;

	public RequestHandlerAlreadyRegisteredException(Class<?> type) {
		super("Request handler already registered for type: " + type.getName());
		this.requestType = type;
	}

	public Class<?> getRequestType() {
		return requestType;
	}
}
