package tests.base.utils.unitTests;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import base.converters.chain.ConverterChainElement;
import base.converters.chain.ConverterChainElementImpl;

public class ConverterChainElementImplUnitTests {
    
    @Test
    public void from_WhenConverterIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> ConverterChainElementImpl.<Object, String>from(String.class::isInstance, null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void from_WhenPredicateIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> ConverterChainElementImpl.<Object, String>from(null, (obj) -> (String) obj);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void fromWithOnlyOneConverter_WhenConverterIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> ConverterChainElementImpl.<Object, String>from(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void setNext_WhenPredicateIsNull_ShouldThrowNullPointerException() {
        ConverterChainElement<Object, String> converterChainElement = ConverterChainElementImpl
                .<Object, String>from(String.class::isInstance, (obj) -> (String) obj);
        ThrowingCallable actual = () -> converterChainElement.setNext(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
}
