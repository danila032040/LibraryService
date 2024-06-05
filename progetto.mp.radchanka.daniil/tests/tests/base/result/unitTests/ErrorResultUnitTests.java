package tests.base.result.unitTests;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import base.result.ErrorResult;

public class ErrorResultUnitTests {
    @Test
    public void from_WhenErrorMessageIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> ErrorResult.from(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
}
