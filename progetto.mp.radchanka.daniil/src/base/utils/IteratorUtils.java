package base.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class IteratorUtils {
	private IteratorUtils() {

	}

	public static <T> boolean elementsEqual(Iterator<T> a, Iterator<T> b) {
		while (a.hasNext() && b.hasNext()) {
			if (!Objects.equals(a.next(), b.next())) {
				return false;
			}
		}
		return (!a.hasNext() && !b.hasNext());
	}

	public static <T> ArrayList<T> toArrayList(Iterator<T> iterator) {
		ArrayList<T> list = new ArrayList<>();
		iterator.forEachRemaining(list::add);
		return list;
	}
}
