package base.specification.composable;

import base.specification.Specification;

public interface CompositeSpecification<T> extends Specification<T> {
    public default CompositeSpecification<T> and(Specification<T> specification) {
        return new AndSpecification<>(this, specification);
    }
    
    public default CompositeSpecification<T> not() {
        return new NotSpecification<>(this);
    }
    
    public default CompositeSpecification<T> or(Specification<T> specification) {
        return new OrSpecification<>(this, specification);
    }
}
