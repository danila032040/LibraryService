package base.mediator;

public interface RequestDispatcher {
	public <TRequest extends Request<TResult>, TResult> RequestDispatcher registerHandler(
			Class<TRequest> requestType,
			RequestHandler<TRequest, TResult> handler) throws RequestHandlerAlreadyRegisteredException;

	public <TRequest extends Request<TResult>, TResult> TResult sendRequest(
			TRequest request);
}
