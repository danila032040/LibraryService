package tests.domain.author.unitTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import domain.author.AuthorId;

public class AuthorIdUnitTests {
	@Test
	public void createClone_ShouldReturnEqualButNotTheSameInstance() {
		AuthorId id = new AuthorId(0);

		AuthorId actual = id.createClone();

		assertThat(actual).isNotSameAs(id).isEqualTo(id);
	}

	@Test
	public void equals_WhenComparedWithTheDifferentClassObject_ShouldBeFalse() {
		AuthorId id = new AuthorId(1);
		Integer otherInstance = 5;

		@SuppressWarnings("unlikely-arg-type")
		boolean actual = id.equals(otherInstance);

		assertThat(actual).isFalse();
	}
	@Test
	public void equals_WhenComparingWithTheSameInstance_ShouldBeTrue() {
		AuthorId id = new AuthorId(1);

		boolean actual = id.equals(id);

		assertThat(actual).isTrue();
	}

	@Test
	public void equals_WhenIdsAreEqual_ShouldBeTrue() {
		AuthorId id1 = new AuthorId(5);
		AuthorId id2 = new AuthorId(5);

		boolean actual = id1.equals(id2);

		assertThat(actual).isTrue();
	}

	@Test
	public void equals_WhenIdsAreNotEqual_ShouldBeFalse() {
		AuthorId id1 = new AuthorId(5);
		AuthorId id2 = new AuthorId(2);

		boolean actual = id1.equals(id2);

		assertThat(actual).isFalse();
	}

	@Test
	public void hashCode_WhenIdsAreEqual_HashCodesShouldBeEqualForBothInstances() {
		AuthorId id1 = new AuthorId(5);
		AuthorId id2 = new AuthorId(5);

		int actual1 = id1.hashCode();
		int actual2 = id2.hashCode();

		assertThat(actual1).isEqualTo(actual2);
	}

	@Test
	public void hashCode_WhenIdsAreNotEqual_HashCodesShouldBeNotEqualForBothInstances() {
		AuthorId id1 = new AuthorId(5);
		AuthorId id2 = new AuthorId(2);

		int actual1 = id1.hashCode();
		int actual2 = id2.hashCode();

		assertThat(actual1).isNotEqualTo(actual2);
	}
}
