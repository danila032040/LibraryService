package base.mediator;

public interface RequestDispatcher {
	public <TRequest extends Request<TResult>, TResult> void registerHandler(
			Class<TRequest> requestType,
			RequestHandler<TRequest, TResult> handler);

	public <TRequest extends Request<TResult>, TResult> TResult sendRequest(
			TRequest request);
}
