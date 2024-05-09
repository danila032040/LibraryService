package base.repository;

import java.util.Collection;
import java.util.Optional;

import base.ddd.Entity;
import base.specification.Specification;

public interface Repository<TEntity extends Entity<TId>, TId> {
	public void add(TEntity entity);

	public void addRange(Collection<TEntity> entity);

	public Collection<TEntity> get(Specification<TEntity> specification);

	public Collection<TEntity> get(
			Specification<TEntity> specification,
			SortCriteria<TEntity> sortCriteria,
			Pagination pagination);

	public Collection<TEntity> get(
			Specification<TEntity> specification,
			Pagination pagination);

	public Collection<TEntity> getAll();

	public Optional<TEntity> getFirst(Specification<TEntity> specification);

	public void remove(TId entityId);

	public void removeRange(Collection<TId> entityIds);

	public void update(TEntity entity);

	public void updateRange(Collection<TEntity> entity);
}