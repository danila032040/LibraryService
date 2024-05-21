package tests.base.lazyLoading;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import base.lazyLoading.LazyLoad;

public class LazyLoadUnitTests {
	@Test
	public void createInstance_WhenInstanceSupplierIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> new LazyLoad<String>(null);

		assertThatExceptionOfType(NullPointerException.class)
				.isThrownBy(actual);
	}
}
