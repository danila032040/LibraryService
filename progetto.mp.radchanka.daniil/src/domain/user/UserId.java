package domain.user;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import base.cloneable.Cloneable;
import base.ddd.ValueObject;

public class UserId extends ValueObject implements Cloneable<UserId>, Comparable<UserId> {
    private final int id;
    
    public UserId(int id) {
        this.id = id;
    }
    
    @Override
    public UserId createClone() {
        return new UserId(this.getId());
    }
    
    @Override
    protected Iterator<Object> getEqualityComponentsIterator() {
        return List.<Object>of(id).iterator();
    }
    
    public int getId() {
        return id;
    }
    
    @Override
    public int compareTo(UserId other) {
        Objects.requireNonNull(other);
        return Integer.compare(this.getId(), other.getId());
    }
}
