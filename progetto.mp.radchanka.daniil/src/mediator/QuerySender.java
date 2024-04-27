package mediator;

public interface QuerySender {
	public <TQuery extends Query<TResult>, TResult> TResult SendQuery(TQuery query);
}
