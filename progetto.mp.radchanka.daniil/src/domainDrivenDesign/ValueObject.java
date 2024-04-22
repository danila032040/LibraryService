package domainDrivenDesign;

import java.util.Iterator;

import utils.IteratorUtils;

public abstract class ValueObject {

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ValueObject))
			return false;
		ValueObject other = (ValueObject) obj;

		return IteratorUtils
				.elementsEqual(
						this.getEqualityComponentsIterator(),
						other.getEqualityComponentsIterator());
	}
	public abstract Iterator<Object> getEqualityComponentsIterator();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		Iterator<Object> equalityComponentsIterator = getEqualityComponentsIterator();

		while (equalityComponentsIterator.hasNext())
			result = prime * result
					+ equalityComponentsIterator.next().hashCode();

		return result;
	}

}
