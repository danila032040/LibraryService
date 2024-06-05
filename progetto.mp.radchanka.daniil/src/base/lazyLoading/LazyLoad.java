package base.lazyLoading;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public final class LazyLoad<T> {
    private final Supplier<T> instanceSupplier;
    private Optional<T> value;
    
    public LazyLoad(Supplier<T> instanceSupplier) {
        this.instanceSupplier = Objects.requireNonNull(instanceSupplier);
        this.value = Optional.empty();
    }
    
    public T getValue() {
        if (value.isEmpty()) {
            value = Optional.of(instanceSupplier.get());
        }
        return value.get();
    }
    
}
