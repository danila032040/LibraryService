package base.repository.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import base.repository.Pagination;

public class PaginationUnitTests {

	@Test
	public void of_WithNegativePageIndex_ThrowsIllegalArgumentException() {
		ThrowingCallable actual = () -> Pagination.of(-1, 1);

		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(actual)
				.withMessage("Page index must be positive");
	}

	@Test
	public void of_WithZeroPageSize_ThrowsIllegalArgumentException() {
		ThrowingCallable actual = () -> Pagination.of(5, 0);

		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(actual)
				.withMessage("Page size must be greater than 0");
	}

	@Test
	public void of_WithNegativePageSize_ThrowsIllegalArgumentException() {
		ThrowingCallable actual = () -> Pagination.of(5, -1);

		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(actual)
				.withMessage("Page size must be greater than 0");
	}

	@Test
	public void of_ShouldCorrectlyCreatePagination() {
		int expectedPageIndex = 5;
		int expectedPageSize = 5;

		Pagination actual = Pagination.of(expectedPageIndex, expectedPageSize);

		assertThat(actual.getPageIndex()).isEqualTo(expectedPageIndex);
		assertThat(actual.getPageSize()).isEqualTo(expectedPageSize);
	}
}
