package base.result;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class ValidationResult {
    private final List<ErrorResult> errors;
    
    private ValidationResult() {
        this.errors = new ArrayList<>();
    }
    
    public static ValidationResult create() {
        return new ValidationResult();
    }
    
    public ValidationResult withErrorIf(Supplier<Boolean> booleanSupplier, ErrorResult error) {
        return withErrorIf(booleanSupplier, () -> error);
    }
    
    public ValidationResult withErrorIf(Supplier<Boolean> booleanSupplier, Supplier<ErrorResult> errorSupplier) {
        Objects.requireNonNull(booleanSupplier);
        Objects.requireNonNull(errorSupplier);
        
        if (booleanSupplier.get()) {
            return withError(errorSupplier.get());
        }
        return this;
    }
    
    public ValidationResult withError(ErrorResult error) {
        Objects.requireNonNull(error);
        errors.add(error);
        return this;
    }
    
    public ValidationResult unionWith(ValidationResult validationResult) {
        Objects.requireNonNull(validationResult);
        this.errors.addAll(validationResult.errors);
        return this;
    }
    
    public boolean isValid() {
        return errors.isEmpty();
    }
    
    public List<ErrorResult> getErrors() {
        return errors;
    }
}
