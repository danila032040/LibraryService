package repository;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class UnitOfWorkImpl implements UnitOfWork {

	private final Map<Class<?>, Repository<?, ?>> repositories;

	public UnitOfWorkImpl(Collection<Repository<?, ?>> repositories) {
		this.repositories = repositories.stream()
				.collect(Collectors.toMap(key -> key.getClass(), value -> value));
	}

	@Override
	public void SaveChanges() {
		// TODO Auto-generated method stub
	}

}
