package repository;

import java.util.Collection;

import specification.Specification;

public interface SpecificationExecutableRepository<T> {
    public Collection<T> get(Specification<T> specification);

    public Collection<T> get(Specification<T> specification, Pagination pagination);
}
