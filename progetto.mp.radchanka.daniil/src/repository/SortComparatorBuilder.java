package repository;

import java.util.Comparator;
import java.util.function.Function;

public class SortComparatorBuilder<TEntity> {
	private Comparator<TEntity> compiledComparator;

	public Comparator<TEntity> getCompiledComparator() {
		return compiledComparator;
	}

	public <U extends Comparable<? super U>> SortComparatorBuilder<TEntity> sortBy(
			Function<TEntity, ? extends U> fieldExpression) {
		compiledComparator = createComparatorFromAFieldExpression(
				fieldExpression);
		return this;
	}

	public <U extends Comparable<? super U>> SortComparatorBuilder<TEntity> sortByDesc(
			Function<TEntity, ? extends U> fieldExpression) {
		compiledComparator = createComparatorFromAFieldExpression(
				fieldExpression).reversed();
		return this;
	}

	public <U extends Comparable<? super U>> SortComparatorBuilder<TEntity> thenSortBy(
			Function<TEntity, ? extends U> fieldExpression) {
		if (compiledComparator == null)
			throw new IllegalStateException(
					"thenSortBy should be only used in pair with at least one preceding sortBy/sortByDesc method.");
		compiledComparator = compiledComparator
				.thenComparing(
						createComparatorFromAFieldExpression(fieldExpression));
		return this;
	}

	public <U extends Comparable<? super U>> SortComparatorBuilder<TEntity> thenSortByDesc(
			Function<TEntity, ? extends U> fieldExpression) {
		if (compiledComparator == null)
			throw new IllegalStateException(
					"thenSortByDesc should be only used in pair with at least one preceding sortBy/sortByDesc method.");
		compiledComparator = compiledComparator
				.thenComparing(
						createComparatorFromAFieldExpression(fieldExpression)
								.reversed());
		return this;
	}

	private <U extends Comparable<? super U>> Comparator<TEntity> createComparatorFromAFieldExpression(
			Function<TEntity, ? extends U> fieldExpression) {
		return Comparator.comparing(fieldExpression);
	}
}
