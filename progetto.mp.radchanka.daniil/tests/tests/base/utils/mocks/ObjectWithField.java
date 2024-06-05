package tests.base.utils.mocks;

public class ObjectWithField<T> {
    private final T value;
    
    public ObjectWithField(T value) {
        this.value = value;
    }
    
    public T getField() {
        return value;
    }
}
