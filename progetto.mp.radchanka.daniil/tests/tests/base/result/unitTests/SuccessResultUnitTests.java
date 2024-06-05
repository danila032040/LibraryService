package tests.base.result.unitTests;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import base.result.SuccessResult;

public class SuccessResultUnitTests {
    @Test
    public void from_WhenSuccessMessageIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> SuccessResult.from(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
}
