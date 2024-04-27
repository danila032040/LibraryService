package mediator;

public interface QueryHandler<TQuery extends Query<TResult>, TResult> {
	public TResult handle(TQuery query);
}
