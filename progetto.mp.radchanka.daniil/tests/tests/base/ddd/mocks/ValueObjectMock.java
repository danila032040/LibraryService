package tests.base.ddd.mocks;

import java.util.Iterator;

import org.assertj.core.util.Lists;

import base.ddd.ValueObject;

public class ValueObjectMock extends ValueObject {

	private int integerToCompareWith;
	private Object objectToCompareWith;
	private String stringToCompareWith;

	public ValueObjectMock(int integerToCompareWith, Object objectToCompareWith,
			String stringToCompareWith) {
		this.integerToCompareWith = integerToCompareWith;
		this.objectToCompareWith = objectToCompareWith;
		this.stringToCompareWith = stringToCompareWith;
	}

	@Override
	public Iterator<Object> getEqualityComponentsIterator() {
		return Lists
				.list(
						integerToCompareWith,
						objectToCompareWith,
						stringToCompareWith)
				.iterator();
	}
}
