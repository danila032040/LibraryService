package base.mediator;

import java.util.HashMap;
import java.util.Map;

public class RequestDispatcherImpl implements RequestDispatcher {
	private final Map<Class<? extends Request<?>>, RequestHandler<? extends Request<?>, ?>> handlers;

	public RequestDispatcherImpl() {
		handlers = new HashMap<>();
	}

	public <TRequest extends Request<TResult>, TResult> void registerHandler(
			Class<TRequest> requestType,
			RequestHandler<TRequest, TResult> handler) {
		handlers.put(requestType, handler);
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
