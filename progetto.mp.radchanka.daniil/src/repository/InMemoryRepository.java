package repository;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import domainDrivenDesign.cloneable.CloneableEntity;
import specification.Specification;

public class InMemoryRepository<TEntity extends CloneableEntity<TEntity, TId>, TId>
		implements
			Repository<TEntity, TId> {
	private final Supplier<Collection<TEntity>> resultCollectionFactory;
	private final Collection<TEntity> storage;

	public InMemoryRepository(Collection<TEntity> storage,
			Supplier<Collection<TEntity>> resultCollectionFactory,
			Supplier<TId> uniqueIdSupplier) {
		this.storage = storage;
		this.resultCollectionFactory = resultCollectionFactory;
	}

	@Override
	public void add(TEntity entity) {
		this.storage.add(entity.createClone());
	}

	@Override
	public void addRange(Collection<TEntity> entities) {
		entities.forEach(this::add);
	}

	@Override
	public Collection<TEntity> get(
			Specification<TEntity> specification,
			Pagination pagination) {
		Stream<TEntity> stream = this.storage.stream();

		stream = applySpecification(stream, specification);
		stream = applyPagination(stream, pagination);
		stream = cloneContent(stream);

		return stream.collect(Collectors.toCollection(resultCollectionFactory));
	}

	@Override
	public Collection<TEntity> getAll() {
		Stream<TEntity> stream = this.storage.stream();

		stream = cloneContent(stream);

		return stream.collect(Collectors.toCollection(resultCollectionFactory));
	}

	@Override
	public Optional<TEntity> getFirst(Specification<TEntity> specification) {
		Stream<TEntity> stream = this.storage.stream();

		stream = applySpecification(stream, specification);

		return stream.findFirst().map(TEntity::createClone);
	}

	@Override
	public void remove(TId entityId) {
		this.storage
				.removeIf(
						storageEntity -> storageEntity
								.getId()
								.equals(entityId));
	}

	@Override
	public void removeRange(Collection<TId> entityIds) {
		this.storage.removeIf(entity -> entityIds.contains(entity.getId()));
	}

	@Override
	public void update(TEntity entity) {
		this.remove(entity.getId());
		this.add(entity);
	}

	@Override
	public void updateRange(Collection<TEntity> entities) {
		removeRange(
				entities
						.stream()
						.map(TEntity::getId)
						.collect(Collectors.toUnmodifiableSet()));
		addRange(entities);
	}

	private Stream<TEntity> applyPagination(
			Stream<TEntity> stream,
			Pagination pagination) {
		return stream
				.skip(pagination.getPageIndex() * pagination.getPageSize())
				.limit(pagination.getPageSize());
	}

	private Stream<TEntity> applySpecification(
			Stream<TEntity> stream,
			Specification<TEntity> specification) {
		return stream.filter(specification::isSatisfiedBy);
	}

	private Stream<TEntity> cloneContent(Stream<TEntity> stream) {
		return stream.map(TEntity::createClone);
	}
}
