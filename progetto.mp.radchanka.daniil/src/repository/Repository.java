package repository;

import java.util.Collection;

public interface Repository<T> {
	public void create(T value);

	public void delete(T value);

	public Collection<T> get(RetrieveStrategy<T> retrieveStrategy);

	public T get(RetrieveSingleItemStrategy<T> retrieveStrategy);

	public void update(T value);
}