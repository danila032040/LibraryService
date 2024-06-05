package tests.base.result.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Optional;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import base.result.OneOf2;
import tests.base.result.mocks.ConsumerMock;

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
    
    @Test
    public void match_WhenResult0Provided_ShouldUseOnlyResultConsumer0() {
        OneOf2<Integer, String> oneOf2 = OneOf2.from0(0);
        ConsumerMock<Integer> resultConsumer0 = new ConsumerMock<Integer>();
        ConsumerMock<String> resultConsumer1 = new ConsumerMock<String>();
        oneOf2.match(resultConsumer0, resultConsumer1);
        
        int actualExecutedTimes = resultConsumer0.getAcceptExecutionCount();
        Optional<Integer> actualResult = resultConsumer0.getLastProvidedArgument();
        
        assertThat(actualExecutedTimes).isEqualTo(1);
        assertThat(actualResult).hasValue(0);
    }
    
    @Test
    public void match_WhenResult1Provided_ShouldUseOnlyResultConsumer1() {
        OneOf2<Integer, String> oneOf2 = OneOf2.from1("Test");
        ConsumerMock<Integer> resultConsumer0 = new ConsumerMock<Integer>();
        ConsumerMock<String> resultConsumer1 = new ConsumerMock<String>();
        oneOf2.match(resultConsumer0, resultConsumer1);
        
        int actualExecutedTimes = resultConsumer1.getAcceptExecutionCount();
        Optional<String> actualResult = resultConsumer1.getLastProvidedArgument();
        
        assertThat(actualExecutedTimes).isEqualTo(1);
        assertThat(actualResult).hasValue("Test");
    }
    
}
