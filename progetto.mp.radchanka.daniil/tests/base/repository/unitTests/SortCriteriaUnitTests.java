package base.repository.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;
import java.util.Comparator;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import base.repository.SortCriteria;
import base.repository.mocks.EntityMock;

public class SortCriteriaUnitTests {
	private ArrayList<EntityMock> entitiesUsedForCheckingCorrectnessOfComparator;

	@Before
	public void Setup() {
		entitiesUsedForCheckingCorrectnessOfComparator = Lists
				.newArrayList(
						new EntityMock(3, -1),
						new EntityMock(1, 1),
						new EntityMock(2, 3),
						new EntityMock(5, -1),
						new EntityMock(-4, -5));
	}

	@Test
	public void sortBy_WhenFieldExpressionIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> SortCriteria.sortBy(null);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

	@Test
	public void sortByDesc_WhenFieldExpressionIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> SortCriteria.sortByDesc(null);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

	@Test
	public void sortBy_getSortComparator_ShouldReturnCorrectComparator() {
		Comparator<EntityMock> expected = Comparator
				.<EntityMock, Integer>comparing(x -> x.getId());

		Comparator<EntityMock> actual = SortCriteria
				.<EntityMock, Integer>sortBy(x -> x.getId())
				.getSortComparator();

		assertThat(
				entitiesUsedForCheckingCorrectnessOfComparator
						.stream()
						.sorted(actual))
				.containsExactly(
						entitiesUsedForCheckingCorrectnessOfComparator
								.stream()
								.sorted(expected)
								.toArray(size -> new EntityMock[size]));
	}

	@Test
	public void sortByDesc_getSortComparator_ShouldReturnCorrectComparator() {
		Comparator<EntityMock> expected = Comparator
				.<EntityMock, Integer>comparing(x -> x.getId())
				.reversed();

		Comparator<EntityMock> actual = SortCriteria
				.<EntityMock, Integer>sortByDesc(x -> x.getId())
				.getSortComparator();

		assertThat(
				entitiesUsedForCheckingCorrectnessOfComparator
						.stream()
						.sorted(actual))
				.containsExactly(
						entitiesUsedForCheckingCorrectnessOfComparator
								.stream()
								.sorted(expected)
								.toArray(size -> new EntityMock[size]));
	}

	@Test
	public void thenSortBy_getSortComparator_ShouldReturnCorrectComparator() {
		Comparator<EntityMock> expected = Comparator
				.<EntityMock, Integer>comparing(x -> x.getId())
				.thenComparing(x -> x.getSomeFieldUsedForSortBy());

		Comparator<EntityMock> actual = SortCriteria
				.<EntityMock, Integer>sortBy(x -> x.getId())
				.thenSortBy(x -> x.getSomeFieldUsedForSortBy())
				.getSortComparator();

		assertThat(
				entitiesUsedForCheckingCorrectnessOfComparator
						.stream()
						.sorted(actual))
				.containsExactly(
						entitiesUsedForCheckingCorrectnessOfComparator
								.stream()
								.sorted(expected)
								.toArray(size -> new EntityMock[size]));
	}

	@Test
	public void thenSortByDesc_getSortComparator_ShouldReturnCorrectComparator() {
		Comparator<EntityMock> expected = Comparator
				.<EntityMock, Integer>comparing(x -> x.getId())
				.thenComparing(
						Comparator
								.<EntityMock, Integer>comparing(
										x -> x.getSomeFieldUsedForSortBy())
								.reversed());

		Comparator<EntityMock> actual = SortCriteria
				.<EntityMock, Integer>sortBy(x -> x.getId())
				.thenSortByDesc(x -> x.getSomeFieldUsedForSortBy())
				.getSortComparator();

		assertThat(
				entitiesUsedForCheckingCorrectnessOfComparator
						.stream()
						.sorted(actual))
				.containsExactly(
						entitiesUsedForCheckingCorrectnessOfComparator
								.stream()
								.sorted(expected)
								.toArray(size -> new EntityMock[size]));
	}
}
