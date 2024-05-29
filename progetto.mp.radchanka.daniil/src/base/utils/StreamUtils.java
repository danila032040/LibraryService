package base.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public final class StreamUtils {
	public static <T> Stream<T> distinct(Stream<T> stream) {
		Set<T> temp = new HashSet<T>();
		return stream.filter(n -> !temp.add(n));
	}
	private StreamUtils() {

	}
}
