package base.result;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ValidationResult {
    private final List<Error> errors;
    
    private ValidationResult() {
        this.errors = new ArrayList<>();
    }
    
    public static ValidationResult create() {
        return new ValidationResult();
    }
    
    public ValidationResult withErrorIf(Supplier<Boolean> supplier, Error error) {
        if (supplier.get()) {
            errors.add(error);
        }
        return this;
    }
    
    public ValidationResult withErrorIf(Supplier<Boolean> supplier, Supplier<Error> errorSupplier) {
        if (supplier.get()) {
            errors.add(errorSupplier.get());
        }
        return this;
    }
    
    public ValidationResult withError(Error error) {
        return withErrorIf(() -> true, error);
    }
    
    public ValidationResult unionWith(ValidationResult validationResult) {
        this.errors.addAll(validationResult.errors);
        return this;
    }
    
    public boolean isValid() {
        return errors.size() == 0;
    }
    
    public List<Error> getErrors() {
        return errors;
    }
}
