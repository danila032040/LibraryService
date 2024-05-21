package base.lazyLoading;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public final class LazyLoad<T> {
	private Optional<T> value;
	private Supplier<T> instanceSupplier;

	public LazyLoad(Supplier<T> instanceSupplier) {
		this.instanceSupplier = Objects.requireNonNull(instanceSupplier);
	}

	public T getValue() {
		if (value.isEmpty()) {
			value = Optional.of(instanceSupplier.get());
		}
		return value.get();
	}

}
