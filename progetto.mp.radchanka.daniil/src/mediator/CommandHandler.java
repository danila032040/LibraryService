package mediator;

public interface CommandHandler<TCommand extends Command<TResult>, TResult> {
	public TResult handle(TCommand command);
	default boolean matches(TCommand command) {
		return false;
	}
}
