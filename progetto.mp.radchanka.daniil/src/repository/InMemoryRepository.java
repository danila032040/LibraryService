package repository;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import domainDrivenDesign.Entity;
import specification.Specification;

public class InMemoryRepository<TEntity extends Entity<TId>, TId>
		implements
			Repository<TEntity, TId> {
	private final Function<TEntity, TEntity> entityCloneFactory;
	private final Supplier<Collection<TEntity>> resultCollectionFactory;
	private final Collection<TEntity> storage;

	public InMemoryRepository(Collection<TEntity> storage,
			Supplier<Collection<TEntity>> resultCollectionFactory,
			Function<TEntity, TEntity> entityCloneFactory) {
		this.storage = storage;
		this.entityCloneFactory = entityCloneFactory;
		this.resultCollectionFactory = resultCollectionFactory;
	}

	@Override
	public void add(TEntity entity) {
		this.storage.add(cloneEntity(entity));
	}

	@Override
	public void addRange(Collection<TEntity> entities) {
		entities.forEach(this::add);
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
			Consumer<SortComparatorBuilder<TEntity>> sortOpt,
			Pagination pagination) {
		Stream<TEntity> stream = this.storage.stream();

		SortComparatorBuilder<TEntity> sortComparatorBuilder = new SortComparatorBuilder<TEntity>();
		sortOpt.accept(sortComparatorBuilder);

		stream = applySpecification(stream, specification);
		stream = applySortComparator(
				stream,
				sortComparatorBuilder.getCompiledComparator());
		stream = applyPagination(stream, pagination);
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
	public Collection<TEntity> getAll() {
		Stream<TEntity> stream = this.storage.stream();

		stream = cloneContent(stream);

		return stream.collect(Collectors.toCollection(resultCollectionFactory));
	}

	@Override
	public Optional<TEntity> getFirst(Specification<TEntity> specification) {
		Stream<TEntity> stream = this.storage.stream();

		stream = applySpecification(stream, specification);

		return stream.findFirst().map(this::cloneEntity);
	}

	@Override
	public void remove(TId entityId) {
		removeRange(Collections.singleton(entityId));
	}

	@Override
	public void removeRange(Collection<TId> entityIds) {
		this.storage.removeIf(entity -> entityIds.contains(entity.getId()));
	}

	@Override
	public void update(TEntity entity) {
		updateRange(Collections.singletonList(entity));
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

	private Stream<TEntity> applySortComparator(
			Stream<TEntity> stream,
			Comparator<TEntity> comparator) {
		return stream.sorted(comparator);
	}

	private Stream<TEntity> applySpecification(
			Stream<TEntity> stream,
			Specification<TEntity> specification) {
		return stream.filter(specification::isSatisfiedBy);
	}

	private Stream<TEntity> cloneContent(Stream<TEntity> stream) {
		return stream.map(this::cloneEntity);
	}
	private TEntity cloneEntity(TEntity entity) {
		return entityCloneFactory.apply(entity);
	}
}
