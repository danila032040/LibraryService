package tests.base.ddd.mocks;

import java.util.Iterator;

import org.assertj.core.util.Lists;

import base.ddd.ValueObject;

public class ValueObjectMock extends ValueObject {
    
    private final int integerToCompareWith;
    private final Object objectToCompareWith;
    private final String stringToCompareWith;
    
    public ValueObjectMock(int integerToCompareWith, Object objectToCompareWith, String stringToCompareWith) {
        this.integerToCompareWith = integerToCompareWith;
        this.objectToCompareWith = objectToCompareWith;
        this.stringToCompareWith = stringToCompareWith;
    }
    
    public Iterator<Object> getEqualityComponentsIteratorPublic() {
        return this.getEqualityComponentsIterator();
    }
    
    @Override
    protected Iterator<Object> getEqualityComponentsIterator() {
        return Lists.list(integerToCompareWith, objectToCompareWith, stringToCompareWith).iterator();
    }
}
