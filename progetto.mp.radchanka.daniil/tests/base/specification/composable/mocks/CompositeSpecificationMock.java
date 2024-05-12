package base.specification.composable.mocks;

import base.specification.composable.CompositeSpecification;

public class CompositeSpecificationMock
		extends
			CompositeSpecification<Integer> {

	private boolean isSatisfied;

	public CompositeSpecificationMock(boolean isSatisfied) {
		this.isSatisfied = isSatisfied;
	}

	@Override
	public boolean isSatisfiedBy(Integer value) {
		return isSatisfied;
	}

}
