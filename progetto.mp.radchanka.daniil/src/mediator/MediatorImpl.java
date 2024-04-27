package mediator;

import java.util.Collection;
import java.util.stream.Stream;

public class MediatorImpl implements Mediator {

	private final Stream<CommandHandler<? extends Command<?>,?>> commandHandlers;
	
	public MediatorImpl(Collection<CommandHandler<? extends Command<?>, ?>> commandHandlers) {
		this.commandHandlers = commandHandlers.stream();
	}	
	@Override
	public <TCommand extends Command<TResult>, TResult> TResult SendCommand(TCommand command) throws Exception {
		// TODO Auto-generated method stub
		commandHandlers.filter(x->{
			var test = x.getClass().getGenericSuperclass()
			return true;
		});
		return null;
	}

	@Override
	public <TQuery extends Query<TResult>, TResult> TResult SendQuery(TQuery query) {
		// TODO Auto-generated method stub
		return null;
	}

}
