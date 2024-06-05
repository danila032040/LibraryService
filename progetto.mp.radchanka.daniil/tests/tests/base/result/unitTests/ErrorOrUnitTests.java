package tests.base.result.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Optional;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import base.result.ErrorResult;
import base.result.ErrorOr;
import tests.base.result.mocks.ConsumerMock;

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
        
        ThrowingCallable actual = () -> errorOr.match(null, (ErrorResult error) -> {
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
    
    @Test
    public void match_WhenErrorMessageProvided_ShouldUseOnlyErrorMessageConsumer() {
        ErrorOr<Integer> errorOr = ErrorOr.fromErrorMessage("");
        ConsumerMock<Integer> resultConsumer = new ConsumerMock<Integer>();
        ConsumerMock<ErrorResult> errorMessageConsumer = new ConsumerMock<ErrorResult>();
        errorOr.match(resultConsumer, errorMessageConsumer);
        
        int actualExecutedTimes = errorMessageConsumer.getAcceptExecutionCount();
        Optional<ErrorResult> actualError = errorMessageConsumer.getLastProvidedArgument();
        
        assertThat(actualExecutedTimes).isEqualTo(1);
        assertThat(actualError).hasValueSatisfying(error -> assertThat(error.getErrorMessage()).isEqualTo(""));
    }
    
    @Test
    public void match_WhenResultProvided_ShouldUseOnlyResultConsumer() {
        ErrorOr<Integer> errorOr = ErrorOr.fromResult(0);
        ConsumerMock<Integer> resultConsumer = new ConsumerMock<Integer>();
        ConsumerMock<ErrorResult> errorMessageConsumer = new ConsumerMock<ErrorResult>();
        errorOr.match(resultConsumer, errorMessageConsumer);
        
        int actualExecutedTimes = resultConsumer.getAcceptExecutionCount();
        Optional<Integer> actualResult = resultConsumer.getLastProvidedArgument();
        
        assertThat(actualExecutedTimes).isEqualTo(1);
        assertThat(actualResult).hasValue(0);
    }
}
