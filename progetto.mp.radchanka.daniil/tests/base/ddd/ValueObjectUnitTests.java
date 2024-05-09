package base.ddd;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class ValueObjectUnitTests {

	@Test
	public void equals_WhenAllComparableFieldsAreEqual_ShouldBeTrue() {
		//Arrange
		ValueObjectMock valueObject1 = new ValueObjectMock(1, "", 2);
		ValueObjectMock valueObject2 = new ValueObjectMock(1, "", 2);
		
		//Act
		boolean actual = valueObject1.equals(valueObject2);
		
		//Assert
		assertThat(actual).isTrue();
	}

	@Test
	public void equals_WhenOnlyPrimitiveDataFieldsAreNotEqual_ShouldBeFalse() {
		//Arrange
		ValueObjectMock valueObject1 = new ValueObjectMock(1, "", 2);
		ValueObjectMock valueObject2 = new ValueObjectMock(3, "", 2);

		//Act
		boolean actual = valueObject1.equals(valueObject2);
		
		//Assert
		assertThat(actual).isFalse();
	}

	@Test
	public void equals_WhenOnlyStringDataFieldsAreNotEqual_ShouldBeFalse() {
		//Arrange
		ValueObjectMock valueObject1 = new ValueObjectMock(1, "123", 2);
		ValueObjectMock valueObject2 = new ValueObjectMock(1, "", 2);

		//Act
		boolean actual = valueObject1.equals(valueObject2);
		
		//Assert
		assertThat(actual).isFalse();
	}

	@Test
	public void equals_WhenOnlyObjectFieldReferencesAreNotEqual_ShouldBeFalse() {
		//Arrange
		ValueObjectMock valueObject1 = new ValueObjectMock(1, "", new int[1]);
		ValueObjectMock valueObject2 = new ValueObjectMock(1, "", new int[1]);

		//Act
		boolean actual = valueObject1.equals(valueObject2);
		
		//Assert
		assertThat(actual).isFalse();
	}

	@Test
	public void equals_WhenObjectFieldReferencesAreEqual_ShouldBeTrue() {
		//Arrange
		int[] arr = new int[1];
		ValueObjectMock valueObject1 = new ValueObjectMock(1, "", arr);
		ValueObjectMock valueObject2 = new ValueObjectMock(1, "", arr);

		//Act
		boolean actual = valueObject1.equals(valueObject2);
		
		//Assert
		assertThat(actual).isTrue();
	}

	@Test
	public void hashCode_WhenAllComparableFieldsAreEqual_HashCodesShouldBeEqualForBothValueObjects() {
		//Arrange
		ValueObjectMock valueObject1 = new ValueObjectMock(1, "123", 1);
		ValueObjectMock valueObject2 = new ValueObjectMock(1, "123", 1);
		
		//Act
		int actual1 = valueObject1.hashCode();
		int actual2 = valueObject2.hashCode();
		
		//Assert
		assertThat(actual1).isEqualTo(actual2);
	}
	
	@Test
	public void hashCode_WhenAtLeastOnePairOfComparableFieldsAreNotEqual_HashCodesShouldBeNotEqualForBothValueObjects() {
		//Arrange
		ValueObjectMock valueObject1 = new ValueObjectMock(1, "123", 1);
		ValueObjectMock valueObject2 = new ValueObjectMock(1, "", 1);
		
		//Act
		int actual1 = valueObject1.hashCode();
		int actual2 = valueObject2.hashCode();
		
		//Assert
		assertThat(actual1).isNotEqualTo(actual2);
	}
}
