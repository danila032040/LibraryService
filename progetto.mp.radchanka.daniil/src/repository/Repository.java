package repository;

import java.util.Collection;

public interface Repository<T> {
    public void create(T value);

    public Collection<T> get();

    public Collection<T> get(Pagination pagination);

    public void update(T value);

    public void delete(T value);
}