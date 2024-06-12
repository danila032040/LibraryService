package domain.book;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import base.cloneable.Cloneable;
import base.ddd.ValueObject;

public class BookId extends ValueObject implements Cloneable<BookId>, Comparable<BookId> {
    private final int id;
    
    public BookId(int id) {
        this.id = id;
    }
    
    @Override
    public int compareTo(BookId other) {
        Objects.requireNonNull(other);
        return Integer.compare(this.getId(), other.getId());
    }
    
    @Override
    public BookId createClone() {
        return new BookId(this.getId());
    }
    
    public int getId() {
        return id;
    }
    
    @Override
    protected Iterator<Object> getEqualityComponentsIterator() {
        return List.<Object>of(id).iterator();
    }
}
