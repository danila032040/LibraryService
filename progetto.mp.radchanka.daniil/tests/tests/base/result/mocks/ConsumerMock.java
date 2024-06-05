package tests.base.result.mocks;

import java.util.Optional;
import java.util.function.Consumer;

public class ConsumerMock<T> implements Consumer<T> {
    private int acceptExecutionCount;
    private Optional<T> lastProvidedArgument;
    
    public ConsumerMock() {
        this.acceptExecutionCount = 0;
        this.lastProvidedArgument = Optional.empty();
    }
    
    @Override
    public void accept(T t) {
        acceptExecutionCount++;
        lastProvidedArgument = Optional.of(t);
    }
    
    public int getAcceptExecutionCount() {
        return acceptExecutionCount;
    }
    
    public Optional<T> getLastProvidedArgument() {
        return lastProvidedArgument;
    }
}
