package base.specification;

public interface Specification<T> {
    public boolean isSatisfiedBy(T value);
}
