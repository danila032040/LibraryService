package base.repository;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

public class SortCriteria<T> {
	public static <T, U extends Comparable<? super U>> SortCriteria<T> sortBy(
			Function<T, ? extends U> fieldExpression) {
		return new SortCriteria<T>(
				createComparatorFromAFieldExpression(fieldExpression));
	}

	public static <T, U extends Comparable<? super U>> SortCriteria<T> sortByDesc(
			Function<T, ? extends U> fieldExpression) {
		return new SortCriteria<T>(
				createComparatorFromAFieldExpression(fieldExpression)
						.reversed());
	}

	private static <T, U extends Comparable<? super U>> Comparator<T> createComparatorFromAFieldExpression(
			Function<T, ? extends U> fieldExpression) {
		return Comparator.comparing(Objects.requireNonNull(fieldExpression));
	}

	private Comparator<T> sortComparator;

	private SortCriteria(Comparator<T> sortComparator) {
		this.sortComparator = Objects.requireNonNull(sortComparator);
	}

	public Comparator<T> getSortComparator() {
		return this.sortComparator;
	}

	public <U extends Comparable<? super U>> SortCriteria<T> thenSortBy(
			Function<T, ? extends U> fieldExpression) {
		sortComparator = sortComparator
				.thenComparing(
						createComparatorFromAFieldExpression(fieldExpression));
		return this;
	}

	public <U extends Comparable<? super U>> SortCriteria<T> thenSortByDesc(
			Function<T, ? extends U> fieldExpression) {
		sortComparator = sortComparator
				.thenComparing(
						createComparatorFromAFieldExpression(fieldExpression)
								.reversed());
		return this;
	}
}
