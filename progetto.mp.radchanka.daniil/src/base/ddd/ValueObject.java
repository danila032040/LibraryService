package base.ddd;

import java.util.Iterator;
import java.util.Objects;

import base.utils.IteratorUtils;

public abstract class ValueObject {
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ValueObject)) {
            return false;
        }
        ValueObject other = (ValueObject) obj;
        
        return IteratorUtils.elementsEqual(this.getEqualityComponentsIterator(), other.getEqualityComponentsIterator());
    }
    
    protected abstract Iterator<Object> getEqualityComponentsIterator();
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        Iterator<Object> equalityComponentsIterator = getEqualityComponentsIterator();
        
        while (equalityComponentsIterator.hasNext()) {
            result = prime * result + Objects.hashCode(equalityComponentsIterator.next());
        }
        
        return result;
    }
    
}
