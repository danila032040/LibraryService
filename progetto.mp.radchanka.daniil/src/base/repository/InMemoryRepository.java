package base.repository;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import base.cloneable.CloneFactory;
import base.ddd.Entity;
import base.specification.Specification;
import base.utils.StreamUtils;

public class InMemoryRepository<TEntity extends Entity<TId>, TId>
		implements
			Repository<TEntity, TId> {

	private final CloneFactory<TEntity> entityCloneFactory;
	private final Supplier<Collection<TEntity>> resultCollectionFactory;
	private final Collection<TEntity> storage;

	public InMemoryRepository(Collection<TEntity> storage,
			Supplier<Collection<TEntity>> resultCollectionFactory,
			CloneFactory<TEntity> entityCloneFactory) {
		this.storage = Objects.requireNonNull(storage);
		this.resultCollectionFactory = Objects
				.requireNonNull(resultCollectionFactory);
		this.entityCloneFactory = Objects.requireNonNull(entityCloneFactory);
	}

	@Override
	public void add(TEntity entity) throws AlreadyExistsException {
		if (this.storage
				.stream()
				.anyMatch(x -> Objects.equals(x.getId(), entity.getId())))
			throw new AlreadyExistsException(
					String
							.format(
									"Entity with id = %s already exists in the storage",
									entity.getId().toString()));
		addToStorage(entity);
	}

	@Override
	public void addRange(Collection<TEntity> entities)
			throws AlreadyExistsException {
		List<TId> alreadyPresentIds = StreamUtils
				.distinct(
						Stream
								.concat(
										this.storage
												.stream()
												.map(Entity::getId),
										entities.stream().map(Entity::getId)))
				.collect(Collectors.toUnmodifiableList());

		if (!alreadyPresentIds.isEmpty()) {
			throw new AlreadyExistsException(
					GenerateAlreadyExistsExceptionMessage(alreadyPresentIds));
		}

		addRangeToStorage(entities);
	}

	@Override
	public Collection<TEntity> get(Specification<TEntity> specification) {
		Stream<TEntity> stream = this.storage.stream();

		stream = applySpecification(stream, specification);
		stream = cloneContent(stream);

		return stream.collect(Collectors.toCollection(resultCollectionFactory));
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
	public Collection<TEntity> get(
			Specification<TEntity> specification,
			SortCriteria<TEntity> sortCriteria,
			Pagination pagination) {
		Stream<TEntity> stream = this.storage.stream();

		stream = applySpecification(stream, specification);
		stream = applySortCriteria(stream, sortCriteria);
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

		return stream.findFirst().map(entityCloneFactory::createClone);
	}

	@Override
	public void remove(TId entityId) {
		removeFromStorage(entityId);
	}

	@Override
	public void removeRange(Collection<TId> entityIds) {
		removeRangeFromStorage(entityIds);
	}

	@Override
	public void update(TEntity entity) {
		removeFromStorage(entity.getId());
		addToStorage(entity);
	}

	@Override
	public void updateRange(Collection<TEntity> entities) {
		removeRangeFromStorage(
				entities
						.stream()
						.map(TEntity::getId)
						.collect(Collectors.toUnmodifiableSet()));
		addRangeToStorage(entities);
	}

	private void addRangeToStorage(Collection<TEntity> entities) {
		this.storage
				.addAll(
						entities
								.stream()
								.map(entityCloneFactory::createClone)
								.collect(Collectors.toUnmodifiableList()));
	}

	private void addToStorage(TEntity entity) {
		this.storage.add(entityCloneFactory.createClone(entity));
	}

	private Stream<TEntity> applyPagination(
			Stream<TEntity> stream,
			Pagination pagination) {
		return stream
				.skip(pagination.getPageIndex() * pagination.getPageSize())
				.limit(pagination.getPageSize());
	}

	private Stream<TEntity> applySortCriteria(
			Stream<TEntity> stream,
			SortCriteria<TEntity> sortCriteria) {
		return stream.sorted(sortCriteria.getSortComparator());
	}

	private Stream<TEntity> applySpecification(
			Stream<TEntity> stream,
			Specification<TEntity> specification) {
		return stream.filter(specification::isSatisfiedBy);
	}

	private Stream<TEntity> cloneContent(Stream<TEntity> stream) {
		return stream.map(entityCloneFactory::createClone);
	}

	private String GenerateAlreadyExistsExceptionMessage(
			Collection<TId> alreadyPresentIds)
			throws AlreadyExistsException {
		StringBuilder exceptionMessage = new StringBuilder(
				"Entities with ids = {");
		for (TId alreadyPresentId : alreadyPresentIds) {
			exceptionMessage.append(alreadyPresentId);
			exceptionMessage.append(", ");
		}
		exceptionMessage
				.delete(
						exceptionMessage.length() - 2,
						exceptionMessage.length());
		exceptionMessage.append("} are already in the storage");

		return exceptionMessage.toString();
	}

	private void removeFromStorage(TId entityId) {
		this.storage.removeIf(entity -> entity.getId() == entityId);
	}

	private void removeRangeFromStorage(Collection<TId> entityIds) {
		this.storage.removeIf(entity -> entityIds.contains(entity.getId()));
	}
}
