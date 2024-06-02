package tests.base.result;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import base.result.OneOf2;

public class OneOf2UnitTests {

	@Test
	public void from0_WhenResultIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> OneOf2.from0(null);
		assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
	}

	@Test
	public void from1_WhenResultIsNull_ShouldThrowNullPointerException() {
		ThrowingCallable actual = () -> OneOf2.from1(null);
		assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
	}

	@Test
	public void match_WhenResultConsumer0IsNull_ShouldThrowNullPointerException() {
		OneOf2<Integer, String> oneOf2 = OneOf2.from0(0);

		ThrowingCallable actual = () -> oneOf2.match(null, (String str) -> {
		});
		assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
	}

	@Test
	public void match_WhenResultConsumer1IsNull_ShouldThrowNullPointerException() {
		OneOf2<Integer, String> oneOf2 = OneOf2.from0(0);

		ThrowingCallable actual = () -> oneOf2.match((Integer integer) -> {
		}, null);
		assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
	}

}
