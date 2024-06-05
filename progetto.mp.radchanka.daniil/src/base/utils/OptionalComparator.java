package base.utils;

import java.util.Comparator;
import java.util.Optional;

public class OptionalComparator<T extends Comparable<T>> implements Comparator<Optional<T>> {
    
    @Override
    public int compare(Optional<T> o1, Optional<T> o2) {
        if (!o1.isPresent() && !o2.isPresent()) {
            return 0;
        }
        if (!o1.isPresent()) {
            return -1;
        }
        if (!o2.isPresent()) {
            return 1;
        }
        return o1.get().compareTo(o2.get());
    }
    
}
