package repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface RetrieveSingleItemStrategy<T> {
	public Optional<T> retrieve(Stream<T> dataStream);
}
