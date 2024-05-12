package base.specification.composable;

import java.util.Objects;

import base.specification.Specification;

public final class OrSpecification<T> extends CompositeSpecification<T> {
	private final Specification<T> _leftSpecification;
	private final Specification<T> _rightSpecification;

	public OrSpecification(Specification<T> leftSpecification,
			Specification<T> rightSpecification) {
		Objects.requireNonNull(leftSpecification);
		Objects.requireNonNull(rightSpecification);
		_leftSpecification = leftSpecification;
		_rightSpecification = rightSpecification;
	}

	@Override
	public boolean isSatisfiedBy(T value) {
		return _leftSpecification.isSatisfiedBy(value)
				|| _rightSpecification.isSatisfiedBy(value);
	}

}
