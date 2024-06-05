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
    
    public ValidationResult withErrorIf(Supplier<Boolean> supplier, ErrorResult error) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(error);
        
        if (supplier.get()) {
            errors.add(error);
        }
        return this;
    }
    
    public ValidationResult withErrorIf(Supplier<Boolean> supplier, Supplier<ErrorResult> errorSupplier) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(errorSupplier);
        
        if (supplier.get()) {
            errors.add(errorSupplier.get());
        }
        return this;
    }
    
    public ValidationResult withError(ErrorResult error) {
        Objects.requireNonNull(error);
        return withErrorIf(() -> true, error);
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
