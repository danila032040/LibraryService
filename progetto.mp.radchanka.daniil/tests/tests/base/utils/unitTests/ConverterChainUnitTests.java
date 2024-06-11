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

public class ConverterChainUnitTests {
    
    @Test
    public void converterChain_ShouldBuildChainInTheCorrectOrder() {
        List<ConverterChainElement<Object, String>> expectedChainElements = Lists
                .list(
                        ConverterChainElement.from(String.class::isInstance, obj -> (String) obj),
                        ConverterChainElement.from(String.class::isInstance, obj -> (String) obj),
                        ConverterChainElement.from(String.class::isInstance, obj -> (String) obj));
        String expectedBaseConverterResult = "myResult";
        Converter<Object, String> baseConverter = (obj) -> expectedBaseConverterResult;
        ConverterChain
                .forConverter(baseConverter)
                .addConverterChainElement(expectedChainElements.get(0))
                .addConverterChainElement(expectedChainElements.get(1))
                .addConverterChainElement(expectedChainElements.get(2));
        
        List<ConverterChainElement<Object, String>> actualChain = Stream
                .iterate(expectedChainElements.get(0), t -> t != null, t -> t.getNext())
                .map(ConverterChainElement.class::cast)
                .collect(Collectors.toList());
        
        assertThat(actualChain.stream().limit(3).collect(Collectors.toList())).hasSameElementsAs(expectedChainElements);
        assertThat(actualChain.get(3).convert("asd")).isSameAs(expectedBaseConverterResult);
    }
    
    @Test
    public void addConverterChainElement_WhenConverterChainElementIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> ConverterChain.forConverter((obj) -> obj).addConverterChainElement(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void addConverterChainElement_WhenConverterIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> ConverterChain
                .forConverter((obj) -> obj)
                .addConverterChainElement(String.class::isInstance, null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void addConverterChainElement_WhenPredicateIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> ConverterChain
                .forConverter((obj) -> obj)
                .addConverterChainElement(null, (obj) -> obj);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void forConverter_WhenConverterIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> ConverterChain.forConverter(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
}
