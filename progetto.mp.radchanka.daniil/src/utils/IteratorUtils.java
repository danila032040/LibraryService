package utils;

import java.util.Iterator;

public final class IteratorUtils {
	public static <T> boolean elementsEqual(Iterator<T> a, Iterator<T> b) {
		while (a.hasNext() && b.hasNext()) {
			if (!a.next().equals(b.next())) {
				return false;
			}
		}
		return (!a.hasNext() && !b.hasNext());
	}
}
