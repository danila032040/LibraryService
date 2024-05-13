package tests.base.ddd.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import tests.base.ddd.mocks.ValueObjectMock;

public class ValueObjectUnitTests {

	@Test
	public void equals_WhenAllComparableFieldsAreEqual_ShouldBeTrue() {
		// Arrange
		ValueObjectMock valueObject1 = new ValueObjectMock(1, 2, "");
		ValueObjectMock valueObject2 = new ValueObjectMock(1, 2, "");

		// Act
		boolean actual = valueObject1.equals(valueObject2);

		// Assert
		assertThat(actual).isTrue();
	}

	@Test
	public void equals_WhenComparedWithTheDifferentClassObject_ShouldBeFalse() {
		// Arrange
		ValueObjectMock valueObject1 = new ValueObjectMock(1, 2, "");
		Integer otherInstance = 5;

		// Act
		@SuppressWarnings("unlikely-arg-type")
		boolean actual = valueObject1.equals(otherInstance);

		// Assert
		assertThat(actual).isFalse();
	}

	@Test
	public void equals_WhenComparingWithTheSameInstance_ShouldBeTrue() {
		// Arrange
		ValueObjectMock valueObject = new ValueObjectMock(1, 2, "");

		// Act
		boolean actual = valueObject.equals(valueObject);

		// Assert
		assertThat(actual).isTrue();
	}

	@Test
	public void equals_WhenObjectFieldReferencesAreEqual_ShouldBeTrue() {
		// Arrange
		int[] arr = new int[1];
		ValueObjectMock valueObject1 = new ValueObjectMock(1, arr, "");
		ValueObjectMock valueObject2 = new ValueObjectMock(1, arr, "");

		// Act
		boolean actual = valueObject1.equals(valueObject2);

		// Assert
		assertThat(actual).isTrue();
	}

	@Test
	public void equals_WhenOneOfComparableFieldsIsNull_ShouldNotThrowAnyException() {
		// Arrange
		ValueObjectMock valueObject1 = new ValueObjectMock(1, 2, "");
		ValueObjectMock valueObject2 = new ValueObjectMock(1, null, "");

		// Act
		ThrowingCallable actual = () -> valueObject1.equals(valueObject2);

		// Assert
		assertThatNoException().isThrownBy(actual);
	}

	@Test
	public void equals_WhenOnlyObjectFieldReferencesAreNotEqual_ShouldBeFalse() {
		// Arrange
		ValueObjectMock valueObject1 = new ValueObjectMock(1, new int[1], "");
		ValueObjectMock valueObject2 = new ValueObjectMock(1, new int[1], "");

		// Act
		boolean actual = valueObject1.equals(valueObject2);

		// Assert
		assertThat(actual).isFalse();
	}

	@Test
	public void equals_WhenOnlyPrimitiveDataFieldsAreNotEqual_ShouldBeFalse() {
		// Arrange
		ValueObjectMock valueObject1 = new ValueObjectMock(1, 2, "");
		ValueObjectMock valueObject2 = new ValueObjectMock(3, 2, "");

		// Act
		boolean actual = valueObject1.equals(valueObject2);

		// Assert
		assertThat(actual).isFalse();
	}

	@Test
	public void equals_WhenOnlyStringDataFieldsAreNotEqual_ShouldBeFalse() {
		// Arrange
		ValueObjectMock valueObject1 = new ValueObjectMock(1, 2, "123");
		ValueObjectMock valueObject2 = new ValueObjectMock(1, 2, "");

		// Act
		boolean actual = valueObject1.equals(valueObject2);

		// Assert
		assertThat(actual).isFalse();
	}

	@Test
	public void hashCode_WhenAllComparableFieldsAreEqual_HashCodesShouldBeEqualForBothValueObjects() {
		// Arrange
		ValueObjectMock valueObject1 = new ValueObjectMock(1, 1, "123");
		ValueObjectMock valueObject2 = new ValueObjectMock(1, 1, "123");

		// Act
		int actual1 = valueObject1.hashCode();
		int actual2 = valueObject2.hashCode();

		// Assert
		assertThat(actual1).isEqualTo(actual2);
	}

	@Test
	public void hashCode_WhenAtLeastOnePairOfComparableFieldsAreNotEqual_HashCodesShouldBeNotEqualForBothValueObjects() {
		// Arrange
		ValueObjectMock valueObject1 = new ValueObjectMock(1, 1, "123");
		ValueObjectMock valueObject2 = new ValueObjectMock(1, 1, "");

		// Act
		int actual1 = valueObject1.hashCode();
		int actual2 = valueObject2.hashCode();

		// Assert
		assertThat(actual1).isNotEqualTo(actual2);
	}

	@Test
	public void hashCode_WhenOneOfComparableFieldsIsNull_ShouldNotThrowAnyException() {
		// Arrange
		ValueObjectMock valueObject1 = new ValueObjectMock(1, null, "");

		// Act
		ThrowingCallable actual = () -> valueObject1.hashCode();

		// Assert
		assertThatNoException().isThrownBy(actual);
	}
}
