package base.specification.composable.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import base.specification.composable.AndSpecification;
import base.specification.composable.CompositeSpecification;
import base.specification.composable.NotSpecification;
import base.specification.composable.OrSpecification;
import base.specification.composable.mocks.CompositeSpecificationMock;

public class CompositeSpecificationUnitTests {

	@Test
	public void and_ShouldReturnAndSpecificationInstance() {
		CompositeSpecificationMock specification1 = new CompositeSpecificationMock(
				true);
		CompositeSpecificationMock specification2 = new CompositeSpecificationMock(
				true);

		CompositeSpecification<Integer> actual = specification1
				.and(specification2);

		assertThat(actual).isInstanceOf(AndSpecification.class);
	}

	@Test
	public void and_WhenNullSpecificationIsPassed_ShouldThrowNullPointerException() {
		CompositeSpecificationMock specification = new CompositeSpecificationMock(
				true);

		ThrowingCallable actual = () -> specification.and(null);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}

	@Test
	public void not_ShouldReturnAndSpecificationInstance() {
		CompositeSpecificationMock specification = new CompositeSpecificationMock(
				true);

		CompositeSpecification<Integer> actual = specification.not();

		assertThat(actual).isInstanceOf(NotSpecification.class);
	}

	@Test
	public void or_ShouldReturnOrSpecificationInstance() {
		CompositeSpecificationMock specification1 = new CompositeSpecificationMock(
				true);
		CompositeSpecificationMock specification2 = new CompositeSpecificationMock(
				true);

		CompositeSpecification<Integer> actual = specification1
				.or(specification2);

		assertThat(actual).isInstanceOf(OrSpecification.class);
	}

	@Test
	public void or_WhenNullSpecificationIsPassed_ShouldThrowNullPointerException() {
		CompositeSpecificationMock specification = new CompositeSpecificationMock(
				true);

		ThrowingCallable actual = () -> specification.or(null);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}
}
