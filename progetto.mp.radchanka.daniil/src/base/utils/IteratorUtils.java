package base.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.BiFunction;

public final class IteratorUtils {
    public static <T> boolean elementsEqual(Iterator<T> a, Iterator<T> b) {
        while (a.hasNext() && b.hasNext()) {
            if (!Objects.equals(a.next(), b.next())) {
                return false;
            }
        }
        return (!a.hasNext() && !b.hasNext());
    }
    
    public static <T, U> U reduceRemaining(Iterator<T> iterator, U identity, BiFunction<U, ? super T, U> accumulator) {
        Objects.requireNonNull(iterator);
        Objects.requireNonNull(accumulator);
        U result = identity;
        while (iterator.hasNext()) {
            T element = iterator.next();
            result = accumulator.apply(result, element);
        }
        return result;
    }
    
    public static <T> ArrayList<T> toArrayList(Iterator<T> iterator) {
        ArrayList<T> list = new ArrayList<>();
        iterator.forEachRemaining(list::add);
        return list;
    }
    
    private IteratorUtils() {
        
    }
}
