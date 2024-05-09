package base.ddd;

import java.util.Iterator;

import org.assertj.core.util.Lists;

public class ValueObjectMock extends ValueObject {

	private int integerToCompareWith;
	private String stringToCompareWith;
	private Object objectToCompareWith;

	public ValueObjectMock(int integerToCompareWith, String stringToCompareWith,
			Object objectToCompareWith) {
		this.integerToCompareWith = integerToCompareWith;
		this.stringToCompareWith = stringToCompareWith;
		this.objectToCompareWith = objectToCompareWith;
	}

	@Override
	public Iterator<Object> getEqualityComponentsIterator() {
		return Lists
				.list(
						integerToCompareWith,
						stringToCompareWith,
						objectToCompareWith)
				.iterator();
	}
}
