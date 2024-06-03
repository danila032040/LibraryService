package base.specification.composable;

import base.specification.Specification;

public interface CompositeSpecification<T> extends Specification<T> {
	public default CompositeSpecification<T> and(
			Specification<T> specification) {
		return new AndSpecification<T>(this, specification);
	}

	public default CompositeSpecification<T> not() {
		return new NotSpecification<T>(this);
	}

	public default CompositeSpecification<T> or(
			Specification<T> specification) {
		return new OrSpecification<T>(this, specification);
	}
}
