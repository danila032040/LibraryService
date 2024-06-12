package tests.base.utils.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Optional;
import java.util.function.BiFunction;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import base.specification.Specification;
import base.utils.SpecificationUtils;
import tests.base.utils.mocks.ObjectWithField;
import tests.base.utils.mocks.ObjectWithOptionalField;

public class SpecificationUtilsUnitTests {
    
    @Test
    public void generateFieldSpecification_WhenComparisonFunctionIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> SpecificationUtils.generateFieldSpecification("", null, String::equals);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void generateFieldSpecification_WhenFieldDoesNotMatch_ShouldReturnFalse() {
        String searchField = "test";
        ObjectWithField<String> entity = new ObjectWithField<String>("not test");
        Specification<ObjectWithField<String>> spec = SpecificationUtils
                .generateFieldSpecification(searchField, ObjectWithField::getField, String::equals);
        
        assertThat(spec.isSatisfiedBy(entity)).isFalse();
    }
    
    @Test
    public void generateFieldSpecification_WhenFieldExtractorIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> SpecificationUtils
                .generateFieldSpecification("", ObjectWithField<String>::getField, null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void generateFieldSpecification_WhenFieldMatches_ShouldReturnTrue() {
        String searchField = "test";
        ObjectWithField<String> entity = new ObjectWithField<String>("test");
        Specification<ObjectWithField<String>> spec = SpecificationUtils
                .generateFieldSpecification(searchField, ObjectWithField::getField, String::equals);
        
        assertThat(spec.isSatisfiedBy(entity)).isTrue();
    }
    
    @Test
    public void generateFieldSpecification_WhenSearchFieldIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> SpecificationUtils
                .generateFieldSpecification(null, ObjectWithField<String>::getField, String::equals);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void generateOptionalFieldSpecification_WhenComparisonFunctionIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> SpecificationUtils
                .generateOptionalFieldSpecification(
                        "",
                        ObjectWithOptionalField<String>::getOptionalField,
                        (BiFunction<String, String, Boolean>) null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void generateOptionalFieldSpecification_WhenFieldExtractorIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> SpecificationUtils.generateOptionalFieldSpecification("", null, String::equals);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void generateOptionalFieldSpecification_WhenFieldIsAbsent_ShouldReturnFalse() {
        String searchField = "test";
        ObjectWithOptionalField<String> entity = new ObjectWithOptionalField<String>(Optional.empty());
        Specification<ObjectWithOptionalField<String>> spec = SpecificationUtils
                .generateOptionalFieldSpecification(
                        searchField,
                        ObjectWithOptionalField::getOptionalField,
                        String::equals);
        
        assertThat(spec.isSatisfiedBy(entity)).isFalse();
    }
    
    @Test
    public void generateOptionalFieldSpecification_WhenFieldIsPresent_ShouldReturnTrueIfMatches() {
        String searchField = "test";
        ObjectWithOptionalField<String> entity = new ObjectWithOptionalField<String>(Optional.of(searchField));
        Specification<ObjectWithOptionalField<String>> spec = SpecificationUtils
                .generateOptionalFieldSpecification(
                        searchField,
                        ObjectWithOptionalField::getOptionalField,
                        String::equals);
        
        assertThat(spec.isSatisfiedBy(entity)).isTrue();
    }
    
    @Test
    public void generateOptionalFieldSpecification_WhenFieldIsPresentAndDoesNotMatch_ShouldReturnFalse() {
        String searchField = "test";
        ObjectWithOptionalField<String> entity = new ObjectWithOptionalField<String>(Optional.of("not test"));
        Specification<ObjectWithOptionalField<String>> spec = SpecificationUtils
                .generateOptionalFieldSpecification(
                        searchField,
                        ObjectWithOptionalField::getOptionalField,
                        String::equals);
        
        assertThat(spec.isSatisfiedBy(entity)).isFalse();
    }
    
    @Test
    public void generateOptionalFieldSpecification_WhenSearchFieldIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> SpecificationUtils
                .generateOptionalFieldSpecification(
                        null,
                        ObjectWithOptionalField<String>::getOptionalField,
                        String::equals);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
}