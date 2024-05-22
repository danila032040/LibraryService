package tests.base.lazyLoading;

import static org.assertj.core.api.Assertions.assertThat;
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
	
	@Test
	public void getValue_WhenUsedTwice_ShouldReturnTheSameInstanceOfTheFirstCall() {
		LazyLoad<Integer> lazyLoad = new LazyLoad<Integer>(()->5);
		Integer expected = lazyLoad.getValue();
		
		Integer actual = lazyLoad.getValue();
		
		assertThat(actual).isSameAs(expected);
	}
	
	@Test
	public void getValue_ShouldReturnTheSameInstanceReturnedInstanceSupplier() {
		Integer expected = 5;
		LazyLoad<Integer> lazyLoad = new LazyLoad<Integer>(()->expected);
		
		Integer actual = lazyLoad.getValue();
		
		assertThat(actual).isSameAs(expected);
	}
}
