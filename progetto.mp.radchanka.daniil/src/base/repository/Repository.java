package base.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import base.ddd.Entity;
import base.specification.Specification;

public interface Repository<TEntity extends Entity<TId>, TId> {
    public void add(TEntity entity) throws AlreadyExistsException;
    
    public void addRange(Collection<TEntity> entity) throws AlreadyExistsException;
    
    public List<TEntity> get(Specification<TEntity> specification);
    
    public List<TEntity> get(Specification<TEntity> specification, Pagination pagination);
    
    public List<TEntity> get(
            Specification<TEntity> specification,
            SortCriteria<TEntity> sortCriteria,
            Pagination pagination);
    
    public List<TEntity> getAll();
    
    public Optional<TEntity> getFirst(Specification<TEntity> specification);
    
    public void remove(TId entityId);
    
    public void removeRange(Collection<TId> entityIds);
    
    public void update(TEntity entity);
    
    public void updateRange(Collection<TEntity> entity);
}