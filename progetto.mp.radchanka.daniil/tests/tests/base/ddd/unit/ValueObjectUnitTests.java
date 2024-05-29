package tests.base.ddd.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import tests.base.ddd.mocks.ValueObjectMock;

public class ValueObjectUnitTests {

	@Test
	public void equals_WhenAllComparableFieldsAreEqual_ShouldBeTrue() {
		ValueObjectMock valueObject1 = new ValueObjectMock(1, 2, "");
		ValueObjectMock valueObject2 = new ValueObjectMock(1, 2, "");

		boolean actual = valueObject1.equals(valueObject2);

		assertThat(actual).isTrue();
	}

	@Test
	public void equals_WhenComparedWithTheDifferentClassObject_ShouldBeFalse() {
		ValueObjectMock valueObject1 = new ValueObjectMock(1, 2, "");
		Integer otherInstance = 5;

		@SuppressWarnings("unlikely-arg-type")
		boolean actual = valueObject1.equals(otherInstance);

		assertThat(actual).isFalse();
	}

	@Test
	public void equals_WhenComparingWithTheSameInstance_ShouldBeTrue() {
		ValueObjectMock valueObject = new ValueObjectMock(1, 2, "");

		boolean actual = valueObject.equals(valueObject);

		assertThat(actual).isTrue();
	}

	@Test
	public void equals_WhenObjectFieldReferencesAreEqual_ShouldBeTrue() {
		int[] arr = new int[1];
		ValueObjectMock valueObject1 = new ValueObjectMock(1, arr, "");
		ValueObjectMock valueObject2 = new ValueObjectMock(1, arr, "");

		boolean actual = valueObject1.equals(valueObject2);

		assertThat(actual).isTrue();
	}

	@Test
	public void equals_WhenOneOfComparableFieldsIsNull_ShouldNotThrowAnyException() {
		ValueObjectMock valueObject1 = new ValueObjectMock(1, 2, "");
		ValueObjectMock valueObject2 = new ValueObjectMock(1, null, "");

		ThrowingCallable actual = () -> valueObject1.equals(valueObject2);

		assertThatNoException().isThrownBy(actual);
	}

	@Test
	public void equals_WhenOnlyObjectFieldReferencesAreNotEqual_ShouldBeFalse() {
		ValueObjectMock valueObject1 = new ValueObjectMock(1, new int[1], "");
		ValueObjectMock valueObject2 = new ValueObjectMock(1, new int[1], "");

		boolean actual = valueObject1.equals(valueObject2);

		assertThat(actual).isFalse();
	}

	@Test
	public void equals_WhenOnlyPrimitiveDataFieldsAreNotEqual_ShouldBeFalse() {
		ValueObjectMock valueObject1 = new ValueObjectMock(1, 2, "");
		ValueObjectMock valueObject2 = new ValueObjectMock(3, 2, "");

		boolean actual = valueObject1.equals(valueObject2);

		assertThat(actual).isFalse();
	}

	@Test
	public void equals_WhenOnlyStringDataFieldsAreNotEqual_ShouldBeFalse() {
		ValueObjectMock valueObject1 = new ValueObjectMock(1, 2, "123");
		ValueObjectMock valueObject2 = new ValueObjectMock(1, 2, "");

		boolean actual = valueObject1.equals(valueObject2);

		assertThat(actual).isFalse();
	}

	@Test
	public void hashCode_WhenAllComparableFieldsAreEqual_HashCodesShouldBeEqualForBothValueObjects() {
		ValueObjectMock valueObject1 = new ValueObjectMock(1, 1, "123");
		ValueObjectMock valueObject2 = new ValueObjectMock(1, 1, "123");

		int actual1 = valueObject1.hashCode();
		int actual2 = valueObject2.hashCode();

		assertThat(actual1).isEqualTo(actual2);
	}

	@Test
	public void hashCode_WhenAtLeastOnePairOfComparableFieldsAreNotEqual_HashCodesShouldBeNotEqualForBothValueObjects() {
		ValueObjectMock valueObject1 = new ValueObjectMock(1, 1, "123");
		ValueObjectMock valueObject2 = new ValueObjectMock(1, 1, "");

		int actual1 = valueObject1.hashCode();
		int actual2 = valueObject2.hashCode();

		assertThat(actual1).isNotEqualTo(actual2);
	}

	@Test
	public void hashCode_WhenOneOfComparableFieldsIsNull_ShouldNotThrowAnyException() {
		ValueObjectMock valueObject1 = new ValueObjectMock(1, null, "");

		ThrowingCallable actual = () -> valueObject1.hashCode();

		assertThatNoException().isThrownBy(actual);
	}
}
