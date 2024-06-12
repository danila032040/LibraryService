package domain.author;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import base.cloneable.Cloneable;
import base.ddd.ValueObject;

public class AuthorId extends ValueObject implements Cloneable<AuthorId>, Comparable<AuthorId> {
    private final int id;
    
    public AuthorId(int id) {
        this.id = id;
    }
    
    @Override
    public int compareTo(AuthorId other) {
        Objects.requireNonNull(other);
        return Integer.compare(this.getId(), other.getId());
    }
    
    @Override
    public AuthorId createClone() {
        return new AuthorId(this.getId());
    }
    
    public int getId() {
        return id;
    }
    
    @Override
    protected Iterator<Object> getEqualityComponentsIterator() {
        return List.<Object>of(id).iterator();
    }
}