package tests.base.utils.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Comparator;
import java.util.Optional;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import base.utils.ComparatorUtils;
import tests.base.utils.mocks.ObjectWithOptionalField;

public class ComparatorUtilsUnitTests {
    
    @Test
    public void comparingOptionalField_WhenBothFieldsAbsent_ShouldReturnZero() {
        Comparator<ObjectWithOptionalField<Integer>> comparator = ComparatorUtils
                .comparingOptionalField(ObjectWithOptionalField<Integer>::getOptionalField);
        
        ObjectWithOptionalField<Integer> obj1 = new ObjectWithOptionalField<Integer>(Optional.empty());
        ObjectWithOptionalField<Integer> obj2 = new ObjectWithOptionalField<Integer>(Optional.empty());
        
        int result = comparator.compare(obj1, obj2);
        
        assertThat(result).isEqualTo(0);
    }
    
    @Test
    public void comparingOptionalField_WhenFieldIsPresent_ShouldReturnCorrectComparison() {
        Comparator<ObjectWithOptionalField<Integer>> comparator = ComparatorUtils
                .comparingOptionalField(ObjectWithOptionalField<Integer>::getOptionalField);
        
        ObjectWithOptionalField<Integer> obj1 = new ObjectWithOptionalField<Integer>(Optional.of(1));
        ObjectWithOptionalField<Integer> obj2 = new ObjectWithOptionalField<Integer>(Optional.of(2));
        
        int result = comparator.compare(obj1, obj2);
        
        assertThat(result).isLessThan(0);
    }
    
    @Test
    public void comparingOptionalField_WhenFieldIsPresentAndEqual_ShouldReturnZero() {
        Comparator<ObjectWithOptionalField<Integer>> comparator = ComparatorUtils
                .comparingOptionalField(ObjectWithOptionalField<Integer>::getOptionalField);
        
        ObjectWithOptionalField<Integer> obj1 = new ObjectWithOptionalField<Integer>(Optional.of(1));
        ObjectWithOptionalField<Integer> obj2 = new ObjectWithOptionalField<Integer>(Optional.of(1));
        
        int result = comparator.compare(obj1, obj2);
        
        assertThat(result).isEqualTo(0);
    }
    
    @Test
    public void comparingOptionalField_WhenFirstFieldIsAbsent_ShouldReturnNegative() {
        Comparator<ObjectWithOptionalField<Integer>> comparator = ComparatorUtils
                .comparingOptionalField(ObjectWithOptionalField<Integer>::getOptionalField);
        
        ObjectWithOptionalField<Integer> obj1 = new ObjectWithOptionalField<Integer>(Optional.empty());
        ObjectWithOptionalField<Integer> obj2 = new ObjectWithOptionalField<Integer>(Optional.of(1));
        
        int result = comparator.compare(obj1, obj2);
        
        assertThat(result).isLessThan(0);
    }
    
    @Test
    public void comparingOptionalField_WhenFirstFieldIsNull_ShouldThrowNullPointerException() {
        Comparator<ObjectWithOptionalField<Integer>> comparator = ComparatorUtils
                .comparingOptionalField(ObjectWithOptionalField<Integer>::getOptionalField);
        
        ThrowingCallable actual = () -> comparator.compare(null, new ObjectWithOptionalField<Integer>(Optional.of(1)));
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void comparingOptionalField_WhenSecondFieldIsAbsent_ShouldReturnPositive() {
        Comparator<ObjectWithOptionalField<Integer>> comparator = ComparatorUtils
                .comparingOptionalField(ObjectWithOptionalField<Integer>::getOptionalField);
        
        ObjectWithOptionalField<Integer> obj1 = new ObjectWithOptionalField<Integer>(Optional.of(1));
        ObjectWithOptionalField<Integer> obj2 = new ObjectWithOptionalField<Integer>(Optional.empty());
        
        int result = comparator.compare(obj1, obj2);
        
        assertThat(result).isGreaterThan(0);
    }
    
    @Test
    public void comparingOptionalField_WhenSecondFieldIsNull_ShouldThrowNullPointerException() {
        Comparator<ObjectWithOptionalField<Integer>> comparator = ComparatorUtils
                .comparingOptionalField(ObjectWithOptionalField<Integer>::getOptionalField);
        
        ThrowingCallable actual = () -> comparator.compare(new ObjectWithOptionalField<Integer>(Optional.of(1)), null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
}