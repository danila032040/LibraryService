package tests.base.utils.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Comparator;
import java.util.Optional;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import base.utils.OptionalComparator;

public class OptionalComparatorUnitTests {
    
    private final Comparator<Optional<Integer>> comparator = new OptionalComparator<>();
    
    @Test
    public void compare_WhenBothFieldsAreEmpty_ShouldReturnZero() {
        Optional<Integer> o1 = Optional.empty();
        Optional<Integer> o2 = Optional.empty();
        
        int actual = comparator.compare(o1, o2);
        
        assertThat(actual).isEqualTo(0);
    }
    
    @Test
    public void compare_WhenFirstFieldIsEmptyAndSecondFieldIsNonEmpty_ShouldReturnNegative() {
        Optional<Integer> o1 = Optional.empty();
        Optional<Integer> o2 = Optional.of(1);
        
        int actual = comparator.compare(o1, o2);
        
        assertThat(actual).isLessThan(0);
    }
    
    @Test
    public void compare_WhenFirstFieldIsNonEmptyAndSecondFieldIsEmpty_ShouldReturnPositive() {
        Optional<Integer> o1 = Optional.of(1);
        Optional<Integer> o2 = Optional.empty();
        
        int actual = comparator.compare(o1, o2);
        
        assertThat(actual).isGreaterThan(0);
    }
    
    @Test
    public void compare_WhenBothFieldsAreNonEmptyAndFirstIsLessThanSecond_ShouldReturnNegative() {
        Optional<Integer> o1 = Optional.of(1);
        Optional<Integer> o2 = Optional.of(2);
        
        int actual = comparator.compare(o1, o2);
        
        assertThat(actual).isLessThan(0);
    }
    
    @Test
    public void compare_WhenBothFieldsAreNonEmptyAndFirstIsGreaterThanSecond_ShouldReturnPositive() {
        Optional<Integer> o1 = Optional.of(2);
        Optional<Integer> o2 = Optional.of(1);
        
        int actual = comparator.compare(o1, o2);
        
        assertThat(actual).isGreaterThan(0);
    }
    
    @Test
    public void compare_WhenBothFieldsAreNonEmptyAndEqual_ShouldReturnZero() {
        Optional<Integer> o1 = Optional.of(1);
        Optional<Integer> o2 = Optional.of(1);
        
        int actual = comparator.compare(o1, o2);
        
        assertThat(actual).isEqualTo(0);
    }
    
    @Test
    public void compare_WhenFirstFieldIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> comparator.compare(null, Optional.empty());
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void compare_WhenSecondFieldIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> comparator.compare(Optional.empty(), null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
}
