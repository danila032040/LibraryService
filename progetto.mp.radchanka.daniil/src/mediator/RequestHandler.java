package mediator;

public interface RequestHandler<TRequest extends Request<TResult>, TResult> {
	public TResult handle(TRequest request);
}
