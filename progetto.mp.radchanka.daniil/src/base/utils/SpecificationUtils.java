package base.utils;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import base.specification.Specification;

public final class SpecificationUtils {
    public static <T, U> Specification<T> generateFieldSpecification(
            Function<T, U> fieldExtractor,
            Specification<U> specificationForField) {
        Objects.requireNonNull(fieldExtractor);
        Objects.requireNonNull(specificationForField);
        return entity -> specificationForField.isSatisfiedBy(fieldExtractor.apply(entity));
    }
    
    public static <T, U> Specification<T> generateFieldSpecification(
            U searchField,
            Function<T, U> fieldExtractor,
            BiFunction<U, U, Boolean> comparisonFunctionStartingWithSearchField) {
        Objects.requireNonNull(searchField);
        Objects.requireNonNull(fieldExtractor);
        Objects.requireNonNull(comparisonFunctionStartingWithSearchField);
        return entity -> comparisonFunctionStartingWithSearchField.apply(searchField, fieldExtractor.apply(entity));
    }
    
    public static <T, U> Specification<T> generateOptionalFieldSpecification(
            Function<T, Optional<U>> fieldExtractor,
            Specification<U> specificationForField) {
        Objects.requireNonNull(fieldExtractor);
        Objects.requireNonNull(specificationForField);
        Specification<T> fieldSpecification = entity -> fieldExtractor
                .apply(entity)
                .map(specificationForField::isSatisfiedBy)
                .orElse(false);
        return fieldSpecification;
    }
    
    public static <T, U> Specification<T> generateOptionalFieldSpecification(
            U searchField,
            Function<T, Optional<U>> fieldExtractor,
            BiFunction<U, U, Boolean> comparisonFunctionStartingWithSearchField) {
        Objects.requireNonNull(searchField);
        Objects.requireNonNull(fieldExtractor);
        Objects.requireNonNull(comparisonFunctionStartingWithSearchField);
        Specification<T> fieldSpecification = entity -> fieldExtractor
                .apply(entity)
                .map(fieldInEntity -> comparisonFunctionStartingWithSearchField.apply(fieldInEntity, searchField))
                .orElse(false);
        return fieldSpecification;
    }
    
    private SpecificationUtils() {
        
    }
}
