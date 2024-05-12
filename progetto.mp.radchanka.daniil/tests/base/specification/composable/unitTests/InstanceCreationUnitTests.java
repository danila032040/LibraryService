package base.specification.composable.unitTests;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import base.specification.composable.AndSpecification;
import base.specification.composable.NotSpecification;
import base.specification.composable.OrSpecification;

public class InstanceCreationUnitTests {
	@Test
	public void andSpecification_CreateInstance_WhenLeftSpecificationIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> new AndSpecification<Integer>(
				null,
				x2 -> true);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

	@Test
	public void andSpecification_CreateInstance_WhenRightSpecificationIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> new AndSpecification<Integer>(
				x1 -> true,
				null);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

	@Test
	public void notSpecification_CreateInstance_WhenSpecificationIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> new NotSpecification<Integer>(null);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

	@Test
	public void orSpecification_CreateInstance_WhenLeftSpecificationIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> new OrSpecification<Integer>(
				null,
				x2 -> true);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

	@Test
	public void orSpecification_CreateInstance_WhenRightSpecificationIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> new OrSpecification<Integer>(
				x1 -> true,
				null);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}
}
