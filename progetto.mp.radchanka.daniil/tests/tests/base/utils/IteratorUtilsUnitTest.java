package tests.base.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.assertj.core.util.Lists;
import org.junit.Test;

import base.utils.IteratorUtils;

public class IteratorUtilsUnitTest {
	@Test
	public void elementsEqual_WhenAllElementsAreEqual_ShouldBeTrue() {
		ArrayList<Integer> list1 = Lists.newArrayList(1, 2, 3, 4, 5);
		ArrayList<Integer> list2 = Lists.newArrayList(1, 2, 3, 4, 5);

		boolean actual = IteratorUtils
				.elementsEqual(list1.iterator(), list2.iterator());

		assertThat(actual).isTrue();
	}

	@Test
	public void elementsEqual_WhenAtLeastOneElementIsNotEqual_ShouldBeFalse() {
		ArrayList<Integer> list1 = Lists.newArrayList(1, 2, 2, 4, 5);
		ArrayList<Integer> list2 = Lists.newArrayList(1, 2, 3, 4, 5);

		boolean actual = IteratorUtils
				.elementsEqual(list1.iterator(), list2.iterator());

		assertThat(actual).isFalse();
	}

	@Test
	public void elementsEqual_WhenFirstCollectionIsShorterWithEqualInitialElements_ShouldBeFalse() {
		ArrayList<Integer> list1 = Lists.newArrayList(1, 2, 3);
		ArrayList<Integer> list2 = Lists.newArrayList(1, 2, 3, 4, 5);

		boolean actual = IteratorUtils
				.elementsEqual(list1.iterator(), list2.iterator());

		assertThat(actual).isFalse();
	}

	@Test
	public void elementsEqual_WhenNullInstanceIsUsed_ShouldNotThrowAnyException() {
		ArrayList<Integer> list1 = Lists.newArrayList(null, null);
		ArrayList<Integer> list2 = Lists.newArrayList(null, 2, 3, null, 5);

		ThrowingCallable actual = () -> IteratorUtils
				.elementsEqual(list1.iterator(), list2.iterator());

		assertThatNoException().isThrownBy(actual);
	}

	@Test
	public void elementsEqual_WhenSecondCollectionIsShorterWithEqualInitialElements_ShouldBeFalse() {
		ArrayList<Integer> list1 = Lists.newArrayList(1, 2, 3, 4, 5);
		ArrayList<Integer> list2 = Lists.newArrayList(1, 2, 3);

		boolean actual = IteratorUtils
				.elementsEqual(list1.iterator(), list2.iterator());

		assertThat(actual).isFalse();
	}

	@Test
	public void toArrayList_ShouldReturnArrayListWithElementsReturnedInOrderFromIterator() {
		ArrayDeque<Integer> list = IntStream
				.of(1, 2, 3, 4, 5, 6)
				.boxed()
				.collect(Collectors.toCollection(ArrayDeque::new));
		Integer[] expected = new Integer[]{6, 5, 4, 3, 2, 1};

		ArrayList<Integer> actual = IteratorUtils
				.toArrayList(list.descendingIterator());

		assertThat(actual).containsExactly(expected);
	}

	@Test
	public void toArrayList_WhenIteratorsDoesNotHaveNextElements_ShouldReturnEmptyArrayList() {
		ArrayList<Integer> expected = Lists.newArrayList();

		ArrayList<Integer> actual = IteratorUtils
				.toArrayList(expected.iterator());

		assertThat(actual).isEmpty();
	}

}
