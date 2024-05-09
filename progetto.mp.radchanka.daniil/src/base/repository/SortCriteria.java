package base.repository;

import java.util.Comparator;
import java.util.function.Function;

public class SortCriteria<T> {
	public static <U extends Comparable<? super U>, T> SortCriteria<T> sortBy(
			Function<T, ? extends U> fieldExpression) {
		return new SortCriteria<T>(
				createComparatorFromAFieldExpression(fieldExpression));
	}

	public static <U extends Comparable<? super U>, T> SortCriteria<T> sortByDesc(
			Function<T, ? extends U> fieldExpression) {
		return new SortCriteria<T>(
				createComparatorFromAFieldExpression(fieldExpression)
						.reversed());
	}

	private static <U extends Comparable<? super U>, T> Comparator<T> createComparatorFromAFieldExpression(
			Function<T, ? extends U> fieldExpression) {
		return Comparator.comparing(fieldExpression);
	}

	private Comparator<T> sortComparator;

	private SortCriteria(Comparator<T> sortComparator) {
		this.sortComparator = sortComparator;
	}

	public Comparator<T> getSortComparator(){
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
