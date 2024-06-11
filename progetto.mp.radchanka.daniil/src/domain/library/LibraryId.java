package domain.library;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import base.cloneable.Cloneable;
import base.ddd.ValueObject;

public class LibraryId extends ValueObject implements Cloneable<LibraryId>, Comparable<LibraryId> {
    private final int id;
    
    public LibraryId(int id) {
        this.id = id;
    }
    
    @Override
    public LibraryId createClone() {
        return new LibraryId(this.getId());
    }
    
    @Override
    protected Iterator<Object> getEqualityComponentsIterator() {
        return List.<Object>of(id).iterator();
    }
    
    public int getId() {
        return id;
    }
    
    @Override
    public int compareTo(LibraryId other) {
        Objects.requireNonNull(other);
        return Integer.compare(this.getId(), other.getId());
    }
}
