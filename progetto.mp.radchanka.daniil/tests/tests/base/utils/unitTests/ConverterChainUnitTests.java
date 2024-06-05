package tests.base.utils.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.assertj.core.util.Lists;
import org.junit.Test;

import base.converters.Converter;
import base.converters.chain.ConverterChain;
import base.converters.chain.ConverterChainElement;
import base.converters.chain.ConverterChainElementImpl;

public class ConverterChainUnitTests {
    @Test
    public void continueWith_WhenConverterChainElementIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> ConverterChain
                .withBaseConverter((obj) -> obj)
                .startWith(String.class::isInstance, obj -> obj)
                .continueWith(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void continueWith_WhenConverterIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> ConverterChain
                .withBaseConverter((obj) -> obj)
                .startWith(String.class::isInstance, obj -> obj)
                .continueWith(String.class::isInstance, null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void continueWith_WhenPredicateIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> ConverterChain
                .withBaseConverter((obj) -> obj)
                .startWith(String.class::isInstance, obj -> obj)
                .continueWith(null, obj -> obj);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void converterChain_ShouldBuildChainInTheCorrectOrder() {
        List<ConverterChainElement<Object, String>> expectedChainElements = Lists
                .list(
                        ConverterChainElementImpl.from(String.class::isInstance, obj -> (String) obj),
                        ConverterChainElementImpl.from(String.class::isInstance, obj -> (String) obj),
                        ConverterChainElementImpl.from(String.class::isInstance, obj -> (String) obj));
        String expectedBaseConverterResult = "myResult";
        Converter<Object, String> baseConverter = (obj) -> expectedBaseConverterResult;
        ConverterChain
                .withBaseConverter(baseConverter)
                .startWith(expectedChainElements.get(0))
                .continueWith(expectedChainElements.get(1))
                .continueWith(expectedChainElements.get(2))
                .buildConverter();
        
        List<ConverterChainElement<Object, String>> actualChain = Stream
                .iterate(expectedChainElements.get(0), t -> t != null, t -> t.getNext())
                .map(ConverterChainElement.class::cast)
                .collect(Collectors.toList());
        
        assertThat(actualChain.stream().limit(3).collect(Collectors.toList())).hasSameElementsAs(expectedChainElements);
        assertThat(actualChain.get(3).convert("asd")).isSameAs(expectedBaseConverterResult);
    }
    
    @Test
    public void startWith_WhenConverterChainElementIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> ConverterChain.withBaseConverter((obj) -> obj).startWith(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void startWith_WhenConverterIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> ConverterChain
                .withBaseConverter((obj) -> obj)
                .startWith(String.class::isInstance, null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void startWith_WhenPredicateIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> ConverterChain.withBaseConverter((obj) -> obj).startWith(null, (obj) -> obj);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void withBaseConverter_WhenConverterIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> ConverterChain.withBaseConverter(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
}
