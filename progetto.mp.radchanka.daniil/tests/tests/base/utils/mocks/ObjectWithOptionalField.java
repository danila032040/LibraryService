package tests.base.utils.mocks;

import java.util.Optional;

public class ObjectWithOptionalField<T> {
    private final Optional<T> value;
    
    public ObjectWithOptionalField(Optional<T> value) {
        this.value = value;
    }
    
    public Optional<T> getOptionalField() {
        return value;
    }
}
