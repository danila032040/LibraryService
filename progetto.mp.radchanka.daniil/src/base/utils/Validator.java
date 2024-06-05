package base.utils;

import base.result.ValidationResult;

public interface Validator<T> {
    public ValidationResult validate(T obj);
}
