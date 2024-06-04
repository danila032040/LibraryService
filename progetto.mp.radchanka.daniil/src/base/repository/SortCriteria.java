package base.repository;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

public class SortCriteria<T> {

	public static <T> SortCriteria<T> sortUsingComparator(Comparator<T> comparator) {
		return new SortCriteria<T>(comparator);
	}

	public static <T, U extends Comparable<? super U>> SortCriteria<T> sortBy(Function<T, ? extends U> fieldExpression,
			SortType sortType) {
		Comparator<T> comparator = createComparatorFromAFieldExpression(fieldExpression);
		if (sortType == SortType.Descending)
			comparator = comparator.reversed();

		return sortUsingComparator(comparator);
	}

	public static <T, U extends Comparable<? super U>> SortCriteria<T> sortByAsc(
			Function<T, ? extends U> fieldExpression) {
		return sortBy(fieldExpression, SortType.Ascending);
	}

	public static <T, U extends Comparable<? super U>> SortCriteria<T> sortByDesc(
			Function<T, ? extends U> fieldExpression) {
		return sortBy(fieldExpression, SortType.Descending);
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

	public SortCriteria<T> thenSortUsingComparator(Comparator<T> comparator) {
		sortComparator = sortComparator.thenComparing(comparator);
		return this;
	}

	public <U extends Comparable<? super U>> SortCriteria<T> thenSortBy(Function<T, ? extends U> fieldExpression,
			SortType sortType) {
		Comparator<T> thenComparator = createComparatorFromAFieldExpression(fieldExpression);
		if (sortType == SortType.Descending)
			thenComparator = thenComparator.reversed();
		return thenSortUsingComparator(thenComparator);
	}

	public <U extends Comparable<? super U>> SortCriteria<T> thenSortByAsc(Function<T, ? extends U> fieldExpression) {
		return this.thenSortBy(fieldExpression, SortType.Ascending);
	}

	public <U extends Comparable<? super U>> SortCriteria<T> thenSortByDesc(Function<T, ? extends U> fieldExpression) {
		return this.thenSortBy(fieldExpression, SortType.Descending);
	}
}
