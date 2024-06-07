package tests.base.utils.mocks;

import java.util.Optional;
import java.util.function.BiFunction;

public class AccumulatorMock<U, T> implements BiFunction<T, U, T> {
    private final BiFunction<T, U, T> functionToApply;
    
    private int executionsCount;
    
    private Optional<U> lastSpecifiedU;
    private Optional<T> lastSpecifiedT;
    
    public AccumulatorMock(BiFunction<T, U, T> functionToApply) {
        this.functionToApply = functionToApply;
        this.executionsCount = 0;
    }
    
    @Override
    public T apply(T t, U u) {
        ++executionsCount;
        lastSpecifiedT = Optional.of(t);
        lastSpecifiedU = Optional.of(u);
        return functionToApply.apply(t, u);
    }
    
    public int getExecutionsCount() {
        return executionsCount;
    }
    
    public Optional<T> getLastSpecifiedT() {
        return lastSpecifiedT;
    }
    
    public Optional<U> getLastSpecifiedU() {
        return lastSpecifiedU;
    }
}
