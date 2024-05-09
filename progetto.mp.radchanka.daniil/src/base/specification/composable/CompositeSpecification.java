package base.specification.composable;

import base.specification.Specification;

public abstract class CompositeSpecification<T> implements Specification<T> {
	public CompositeSpecification<T> and(Specification<T> specification) {
		return new AndSpecification<T>(this, specification);
	}

	public CompositeSpecification<T> not() {
		return new NotSpecification<T>(this);
	}

	public CompositeSpecification<T> or(Specification<T> specification) {
		return new OrSpecification<T>(this, specification);
	}
}
