package repository;

import java.util.Collection;
import java.util.Optional;

import domainDrivenDesign.Entity;
import specification.Specification;

public interface Repository<TEntity extends Entity<TId>, TId> {
	public void add(TEntity entity);
	
	public void addRange(Collection<TEntity> entity);
	
	public Collection<TEntity> getAll();
	
	public Collection<TEntity> get(Specification<TEntity> specification, Pagination pagination);

	public Optional<TEntity> getFirst(Specification<TEntity> specification);

	public void remove(TId entityId);
	
	public void removeRange(Collection<TId> entityIds);

	public void update(TEntity entity);
	
	public void updateRange(Collection<TEntity> entity);
}