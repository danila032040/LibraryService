package base.specification.composable;

import java.util.Objects;

import base.specification.Specification;

public final class NotSpecification<T> extends CompositeSpecification<T> {
	private final Specification<T> _specification;

	public NotSpecification(Specification<T> specification) {
		Objects.requireNonNull(specification);
		_specification = specification;
	}

	@Override
	public boolean isSatisfiedBy(T value) {
		return !_specification.isSatisfiedBy(value);
	}
}
