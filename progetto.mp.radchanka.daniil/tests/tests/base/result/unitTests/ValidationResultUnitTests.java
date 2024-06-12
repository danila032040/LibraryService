package tests.base.result.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.List;
import java.util.function.Supplier;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import base.result.ErrorResult;
import base.result.ValidationResult;

public class ValidationResultUnitTests {
    
    @Test
    public void create_ShouldCreateValidationResultCorrectly() {
        ValidationResult actual = ValidationResult.create();
        
        assertThat(actual).isNotNull();
        assertThat(actual.isValid()).isTrue();
        assertThat(actual.getErrors()).isEmpty();
    }
    
    @Test
    public void getErrors_ShouldReturnAllAddedErrors() {
        ValidationResult result = ValidationResult.create();
        ErrorResult error1 = ErrorResult.from("");
        ErrorResult error2 = ErrorResult.from("");
        
        result.withError(error1).withError(error2);
        
        List<ErrorResult> errors = result.getErrors();
        assertThat(errors).containsExactly(error1, error2);
    }
    
    @Test
    public void isValid_WhenErrorsWereAdded_ShouldReturnFalse() {
        ValidationResult result = ValidationResult.create();
        
        result.withError(ErrorResult.from(""));
        assertThat(result.isValid()).isFalse();
    }
    
    @Test
    public void isValid_WhenErrorsWereNotAdded_ShouldReturnTrue() {
        ValidationResult result = ValidationResult.create();
        
        assertThat(result.isValid()).isTrue();
    }
    
    @Test
    public void unionWith_ShouldAddErrorsFromTheOtherValidationResult() {
        ValidationResult result1 = ValidationResult.create();
        ErrorResult error1 = ErrorResult.from("");
        result1.withError(error1);
        
        ValidationResult result2 = ValidationResult.create();
        ErrorResult error2 = ErrorResult.from("");
        result2.withError(error2);
        
        result1.unionWith(result2);
        
        assertThat(result1.isValid()).isFalse();
        assertThat(result1.getErrors()).containsExactly(error1, error2);
    }
    
    @Test
    public void unionWith_WhenValidationResultIsNull_ShouldThrowNullPointerException() {
        ValidationResult result = ValidationResult.create();
        
        ThrowingCallable actual = () -> result.unionWith(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void withError_ShouldAddError() {
        ValidationResult result = ValidationResult.create();
        ErrorResult error = ErrorResult.from("");
        
        result.withError(error);
        
        assertThat(result.isValid()).isFalse();
        assertThat(result.getErrors()).containsExactly(error);
    }
    
    @Test
    public void withError_WhenErrorIsNull_ShouldThrowNullPointerException() {
        ValidationResult result = ValidationResult.create();
        
        ThrowingCallable actual = () -> result.withError(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void withErrorIf_WhenConditionIsFalse_ShouldNotAddError() {
        ValidationResult result = ValidationResult.create();
        ErrorResult error = ErrorResult.from("");
        
        result.withErrorIf(() -> false, error);
        
        assertThat(result.isValid()).isTrue();
        assertThat(result.getErrors()).isEmpty();
    }
    
    @Test
    public void withErrorIf_WhenConditionIsFalse_ShouldNotAddErrorFromSupplier() {
        ValidationResult result = ValidationResult.create();
        Supplier<ErrorResult> errorSupplier = () -> ErrorResult.from("");
        
        result.withErrorIf(() -> false, errorSupplier);
        
        assertThat(result.isValid()).isTrue();
        assertThat(result.getErrors()).isEmpty();
    }
    
    @Test
    public void withErrorIf_WhenConditionIsTrue_ShouldAddError() {
        ValidationResult result = ValidationResult.create();
        ErrorResult error = ErrorResult.from("");
        
        result.withErrorIf(() -> true, error);
        
        assertThat(result.isValid()).isFalse();
        assertThat(result.getErrors()).containsExactly(error);
    }
    
    @Test
    public void withErrorIf_WhenConditionIsTrue_ShouldAddErrorFromSupplier() {
        ValidationResult result = ValidationResult.create();
        ErrorResult expectedError = ErrorResult.from("");
        Supplier<ErrorResult> errorSupplier = () -> expectedError;
        
        result.withErrorIf(() -> true, errorSupplier);
        
        assertThat(result.isValid()).isFalse();
        assertThat(result.getErrors()).containsExactly(expectedError);
    }
    
    @Test
    public void withErrorIf_WhenErrorIsNull_ShouldThrowNullPointerException() {
        ValidationResult result = ValidationResult.create();
        
        ThrowingCallable actual = () -> result.withErrorIf(() -> true, (ErrorResult) null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void withErrorIf_WhenErrorSupplierIsNull_ShouldThrowNullPointerException() {
        ValidationResult result = ValidationResult.create();
        
        ThrowingCallable actual = () -> result.withErrorIf(() -> true, (Supplier<ErrorResult>) null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void withErrorIf_WhenSupplierIsNull_ShouldThrowNullPointerException() {
        ValidationResult result = ValidationResult.create();
        
        ThrowingCallable actual = () -> result.withErrorIf(null, ErrorResult.from(""));
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
}
