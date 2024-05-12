package base.mediator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RequestDispatcherImpl implements RequestDispatcher {
	private final Map<Class<? extends Request<?>>, RequestHandler<? extends Request<?>, ?>> handlers;

	public RequestDispatcherImpl() {
		handlers = new HashMap<>();
	}

	public <TRequest extends Request<TResult>, TResult> RequestDispatcher registerHandler(
			Class<TRequest> requestType,
			RequestHandler<TRequest, TResult> handler)
			throws RequestHandlerAlreadyRegisteredException {
		Objects.requireNonNull(requestType);
		Objects.requireNonNull(handler);
		if (handlers.containsKey(requestType))
			throw new RequestHandlerAlreadyRegisteredException(requestType);
		handlers.put(requestType, handler);
		return this;
	}

	public <TRequest extends Request<TResult>, TResult> TResult sendRequest(
			TRequest request) {
		Class<?> requestType = request.getClass();
		RequestHandler<? extends Request<?>, ?> handler = handlers
				.get(requestType);

		if (handler == null)
			throw new RequestHandlerNotFoundException(requestType);

		return handleRequest(request, handler);
	}

	private <TRequest extends Request<TResult>, TResult> TResult handleRequest(
			TRequest request,
			RequestHandler<? extends Request<?>, ?> handler) {
		@SuppressWarnings("unchecked")
		RequestHandler<TRequest, TResult> castedHandler = (RequestHandler<TRequest, TResult>) handler;
		return castedHandler.handle(request);
	}
}
