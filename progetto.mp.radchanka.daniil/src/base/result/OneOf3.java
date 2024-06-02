package base.result;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import base.utils.Pair;

public class OneOf3<T0, T1, T2> {
	public static <T0, T1, T2> OneOf3<T0, T1, T2> from0(T0 result) {
		return new OneOf3<T0, T1, T2>(
				Optional.of(result),
				Optional.empty(),
				Optional.empty());
	}
	public static <T0, T1, T2> OneOf3<T0, T1, T2> from1(T1 result) {
		return new OneOf3<T0, T1, T2>(
				Optional.empty(),
				Optional.of(result),
				Optional.empty());
	}
	public static <T0, T1, T2> OneOf3<T0, T1, T2> from2(T2 result) {
		return new OneOf3<T0, T1, T2>(
				Optional.empty(),
				Optional.empty(),
				Optional.of(result));
	}
	private int presentedResultIndex;

	private final Optional<T0> result0;

	private final Optional<T1> result1;

	private final Optional<T2> result2;

	protected OneOf3(Optional<T0> result0, Optional<T1> result1,
			Optional<T2> result2) {
		List<Integer> selectedResultIndexes = Stream
				.of(
						Pair.of(0, result0.isPresent()),
						Pair.of(1, result1.isPresent()),
						Pair.of(2, result2.isPresent()))
				.filter(Pair::getT1)
				.map(Pair::getT0)
				.collect(Collectors.toList());

		if (selectedResultIndexes.size() > 1)
			throw new IllegalArgumentException(
					"OneOf should contain only one present result, but multiple present results were provided");
		if (selectedResultIndexes.size() == 0) {
			throw new IllegalArgumentException(
					"OneOf should contain only one present result, but none of provided results were present");
		}
		this.result0 = result0;
		this.result1 = result1;
		this.result2 = result2;
		this.presentedResultIndex = selectedResultIndexes.get(0);
	}

	public void match(
			Consumer<T0> resultConsumer0,
			Consumer<T1> resultConsumer1,
			Consumer<T2> resultConsumer2) {
		if (isT0())
			result0.ifPresent(resultConsumer0);
		if (isT1())
			result1.ifPresent(resultConsumer1);
		if (isT2())
			result2.ifPresent(resultConsumer2);
	}

	private boolean isT0() {
		return this.presentedResultIndex == 0;
	}
	private boolean isT1() {
		return this.presentedResultIndex == 1;
	}

	private boolean isT2() {
		return this.presentedResultIndex == 2;
	}
}
