package base.cloneable;

public interface CloneFactory<T> {
	public T createClone(T object);
}
