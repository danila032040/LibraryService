package tests.base.specification.composable.mocks;

import base.specification.composable.CompositeSpecification;

public class CompositeSpecificationMock implements CompositeSpecification<Integer> {
    
    private boolean isSatisfied;
    
    public CompositeSpecificationMock(boolean isSatisfied) {
        this.isSatisfied = isSatisfied;
    }
    
    @Override
    public boolean isSatisfiedBy(Integer value) {
        return isSatisfied;
    }
    
}
