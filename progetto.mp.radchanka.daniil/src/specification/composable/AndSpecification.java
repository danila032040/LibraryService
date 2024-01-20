package specification.composable;

import specification.Specification;

public class AndSpecification<T> extends CompositeSpecification<T> {
    private final Specification<T> _leftSpecification;
    private final Specification<T> _rightSpecification;

    public AndSpecification(Specification<T> leftSpecification, Specification<T> rightSpecification) {
	this._leftSpecification = leftSpecification;
	this._rightSpecification = rightSpecification;
    }

    @Override
    public boolean isSatisfiedBy(T value) {
	return _leftSpecification.isSatisfiedBy(value) && _rightSpecification.isSatisfiedBy(value);
    }
}
