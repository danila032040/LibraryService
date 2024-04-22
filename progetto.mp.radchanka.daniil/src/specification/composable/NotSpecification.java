package specification.composable;

import specification.Specification;

public final class NotSpecification<T> extends CompositeSpecification<T> {
	private final Specification<T> _specification;

	public NotSpecification(Specification<T> specification) {
		_specification = specification;
	}

	@Override
	public boolean isSatisfiedBy(T value) {
		return !_specification.isSatisfiedBy(value);
	}
}
