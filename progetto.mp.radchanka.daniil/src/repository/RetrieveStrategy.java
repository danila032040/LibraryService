package repository;

import java.util.Collection;
import java.util.stream.Stream;

public interface RetrieveStrategy<T> {
	public Collection<T> retrieve(Stream<T> dataStream);
}