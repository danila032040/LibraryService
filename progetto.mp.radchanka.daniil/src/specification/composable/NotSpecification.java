package specification.composable;

import specification.Specification;

public class NotSpecification<T> extends CompositeSpecification<T> {
    private final Specification<T> _specification;

    public NotSpecification(Specification<T> specification) {
	this._specification = specification;
    }

    @Override
    public boolean isSatisfiedBy(T value) {
	return !_specification.isSatisfiedBy(value);
    }
}
