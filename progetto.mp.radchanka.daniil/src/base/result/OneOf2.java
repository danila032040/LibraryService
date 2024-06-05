package base.result;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import base.utils.Pair;

public class OneOf2<T0, T1> {
    public static <T0, T1> OneOf2<T0, T1> from0(T0 result) {
        return new OneOf2<>(Optional.of(result), Optional.empty());
    }
    
    public static <T0, T1> OneOf2<T0, T1> from1(T1 result) {
        return new OneOf2<>(Optional.empty(), Optional.of(result));
    }
    
    private int presentedResultIndex;
    
    private final Optional<T0> result0;
    
    private final Optional<T1> result1;
    
    protected OneOf2(Optional<T0> result0, Optional<T1> result1) {
        List<Integer> selectedResultIndexes = Stream
                .of(Pair.of(0, result0.isPresent()), Pair.of(1, result1.isPresent()))
                .filter(Pair::getRight)
                .map(Pair::getLeft)
                .collect(Collectors.toList());
        
        if (selectedResultIndexes.size() > 1) {
            throw new IllegalArgumentException(
                    "OneOf should contain only one present result, but multiple present results were provided");
        }
        if (selectedResultIndexes.size() == 0) {
            throw new IllegalArgumentException(
                    "OneOf should contain only one present result, but none of provided results were present");
        }
        this.result0 = result0;
        this.result1 = result1;
        this.presentedResultIndex = selectedResultIndexes.get(0);
    }
    
    public boolean isT0() {
        return this.presentedResultIndex == 0;
    }
    
    public boolean isT1() {
        return this.presentedResultIndex == 1;
    }
    
    public void match(Consumer<T0> resultConsumer0, Consumer<T1> resultConsumer1) {
        Objects.requireNonNull(resultConsumer0);
        Objects.requireNonNull(resultConsumer1);
        if (isT0()) {
            result0.ifPresent(resultConsumer0);
        }
        if (isT1()) {
            result1.ifPresent(resultConsumer1);
        }
    }
}
