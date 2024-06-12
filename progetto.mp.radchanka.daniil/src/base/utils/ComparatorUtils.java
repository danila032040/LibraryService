package base.utils;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

public final class ComparatorUtils {
    public static <T, U extends Comparable<U>> Comparator<T> comparingOptionalField(
            Function<T, Optional<U>> keyExtractor) {
        return Comparator.<T, Optional<U>>comparing(keyExtractor, new OptionalComparator<U>());
    }
    
    private ComparatorUtils() {
        
    }
}
