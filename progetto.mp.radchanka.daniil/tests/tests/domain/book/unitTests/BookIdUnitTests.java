package tests.domain.book.unitTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import domain.book.BookId;

public class BookIdUnitTests {

	@Test
	public void equals_WhenComparedWithTheDifferentClassObject_ShouldBeFalse() {
		BookId id = new BookId(1);
		Integer otherInstance = 5;

		@SuppressWarnings("unlikely-arg-type")
		boolean actual = id.equals(otherInstance);

		assertThat(actual).isFalse();
	}

	@Test
	public void equals_WhenComparingWithTheSameInstance_ShouldBeTrue() {
		BookId id = new BookId(1);

		boolean actual = id.equals(id);

		assertThat(actual).isTrue();
	}
	@Test
	public void equals_WhenIdsAreEqual_ShouldBeTrue() {
		BookId id1 = new BookId(5);
		BookId id2 = new BookId(5);

		boolean actual = id1.equals(id2);

		assertThat(actual).isTrue();
	}

	@Test
	public void equals_WhenIdsAreNotEqual_ShouldBeFalse() {
		BookId id1 = new BookId(5);
		BookId id2 = new BookId(2);

		boolean actual = id1.equals(id2);

		assertThat(actual).isFalse();
	}

	@Test
	public void hashCode_WhenIdsAreEqual_HashCodesShouldBeEqualForBothInstances() {
		BookId id1 = new BookId(5);
		BookId id2 = new BookId(5);

		int actual1 = id1.hashCode();
		int actual2 = id2.hashCode();

		assertThat(actual1).isEqualTo(actual2);
	}

	@Test
	public void hashCode_WhenIdsAreNotEqual_HashCodesShouldBeNotEqualForBothInstances() {
		BookId id1 = new BookId(5);
		BookId id2 = new BookId(2);

		int actual1 = id1.hashCode();
		int actual2 = id2.hashCode();

		assertThat(actual1).isNotEqualTo(actual2);
	}

	@Test
	public void createClone_ShouldCreateEqualButNotTheSameInstance() {
		BookId expected = new BookId(0);

		BookId actual = expected.createClone();

		assertThat(actual).isNotSameAs(expected).isEqualTo(expected);
	}
}
