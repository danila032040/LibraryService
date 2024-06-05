package tests.base.result;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import base.result.Error;
import base.result.ErrorOr;

public class ErrorOrUnitTests {
    
    @Test
    public void fromErrorMessage_WhenErrorMessageIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> ErrorOr.fromErrorMessage(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void fromResult_WhenErrorResultIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> ErrorOr.fromResult(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
        
    }
    
    @Test
    public void match_WhenResultConsumerIsNull_ShouldThrowNullPointerException() {
        ErrorOr<Integer> errorOr = ErrorOr.fromResult(0);
        
        ThrowingCallable actual = () -> errorOr.match(null, (Error error) -> {
        });
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void match_WhenErrorMessageConsumerIsNull_ShouldThrowNullPointerException() {
        ErrorOr<Integer> errorOr = ErrorOr.fromErrorMessage("");
        
        ThrowingCallable actual = () -> errorOr.match((Integer) -> {
        }, null);
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
}
