package base.result;

import java.util.Optional;
import java.util.function.Consumer;

public class OneOf2<T0, T1> {
	private Optional<T0> result0;
	private Optional<T1> result1;
	private int index;

	protected OneOf2(Optional<T0> result1, Optional<T1> result2, int index) {
		this.result0 = result1;
		this.result1 = result2;
	}

	public boolean isT0() {
		return index == 0;
	}

	public boolean isT1() {
		return index == 1;
	}

	public static <T0, T1> OneOf2<T0, T1> from1(T0 result) {
		return new OneOf2<T0, T1>(Optional.of(result), Optional.empty(), 0);
	}

	public static <T0, T1> OneOf2<T0, T1> from2(T1 result) {
		return new OneOf2<T0, T1>(Optional.empty(), Optional.of(result), 1);
	}

	public void match(Consumer<T0> result1Consumer, Consumer<T1> result2Consumer) {
		result0.ifPresent(result1Consumer);
		result1.ifPresent(result2Consumer);
	}
}
