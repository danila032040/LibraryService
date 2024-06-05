package base.utils;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import base.specification.Specification;

public final class SpecificationUtils {
    private SpecificationUtils() {
        
    }
    
    public static <T, U> Specification<T> generateOptionalFieldSpecification(
            U searchField,
            Function<T, Optional<U>> fieldExtractor,
            BiFunction<U, U, Boolean> comparisonFunctionStartingWithSearchField) {
        Specification<T> fieldSpecification = entity -> fieldExtractor
                .apply(entity)
                .map(fieldInEntity -> comparisonFunctionStartingWithSearchField.apply(fieldInEntity, searchField))
                .orElse(false);
        return fieldSpecification;
    }
    
    public static <T, U> Specification<T> generateFieldSpecification(
            U searchField,
            Function<T, U> fieldExtractor,
            BiFunction<U, U, Boolean> comparisonFunctionStartingWithSearchField) {
        return entity -> comparisonFunctionStartingWithSearchField.apply(fieldExtractor.apply(entity), searchField);
    }
}
