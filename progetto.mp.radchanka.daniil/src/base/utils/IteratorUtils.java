package base.utils;

import java.util.Iterator;
import java.util.Objects;

public final class IteratorUtils {
	public static <T> boolean elementsEqual(Iterator<T> a, Iterator<T> b) {
		while (a.hasNext() && b.hasNext()) {
			if (!Objects.equals(a.next(), b.next())) {
				return false;
			}
		}
		return (!a.hasNext() && !b.hasNext());
	}
}
