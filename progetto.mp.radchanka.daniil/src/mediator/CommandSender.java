package mediator;

public interface CommandSender {
	public <TCommand extends Command<TResult>, TResult> TResult SendCommand(TCommand command) throws Exception;
}
