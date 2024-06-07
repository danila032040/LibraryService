package tests.domain.book.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import domain.book.BookId;

public class BookIdUnitTests {
    @Test
    public void createClone_ShouldReturnEqualButNotTheSameInstance() {
        BookId id = new BookId(0);
        
        BookId actual = id.createClone();
        
        assertThat(actual).isNotSameAs(id).isEqualTo(id);
    }
    
    @Test
    public void equals_WhenComparedWithTheDifferentClassObject_ShouldBeFalse() {
        BookId id = new BookId(1);
        Integer otherInstance = 5;
        
        @SuppressWarnings("unlikely-arg-type")
        boolean actual = id.equals(otherInstance);
        
        assertThat(actual).isFalse();
    }
    
    @Test
    public void equals_WhenComparingWithTheSameInstance_ShouldBeTrue() {
        BookId id = new BookId(1);
        
        boolean actual = id.equals(id);
        
        assertThat(actual).isTrue();
    }
    
    @Test
    public void equals_WhenIdsAreEqual_ShouldBeTrue() {
        BookId id1 = new BookId(5);
        BookId id2 = new BookId(5);
        
        boolean actual = id1.equals(id2);
        
        assertThat(actual).isTrue();
    }
    
    @Test
    public void equals_WhenIdsAreNotEqual_ShouldBeFalse() {
        BookId id1 = new BookId(5);
        BookId id2 = new BookId(2);
        
        boolean actual = id1.equals(id2);
        
        assertThat(actual).isFalse();
    }
    
    @Test
    public void hashCode_WhenIdsAreEqual_HashCodesShouldBeEqualForBothInstances() {
        BookId id1 = new BookId(5);
        BookId id2 = new BookId(5);
        
        int actual1 = id1.hashCode();
        int actual2 = id2.hashCode();
        
        assertThat(actual1).isEqualTo(actual2);
    }
    
    @Test
    public void hashCode_WhenIdsAreNotEqual_HashCodesShouldBeNotEqualForBothInstances() {
        BookId id1 = new BookId(5);
        BookId id2 = new BookId(2);
        
        int actual1 = id1.hashCode();
        int actual2 = id2.hashCode();
        
        assertThat(actual1).isNotEqualTo(actual2);
    }
    
    @Test
    public void compareTo_WhenIdsAreEqual_ShouldReturnZero() {
        BookId id1 = new BookId(1);
        BookId id2 = new BookId(1);
        
        int result = id1.compareTo(id2);
        
        assertThat(result).isZero();
    }
    
    @Test
    public void compareTo_WhenComparingWithGreaterId_ShouldReturnNegative() {
        BookId id1 = new BookId(1);
        BookId id2 = new BookId(2);
        
        int result = id1.compareTo(id2);
        
        assertThat(result).isNegative();
    }
    
    @Test
    public void compareTo_WhenComparingWithLesserId_ShouldReturnPositive() {
        BookId id1 = new BookId(2);
        BookId id2 = new BookId(1);
        
        int result = id1.compareTo(id2);
        
        assertThat(result).isPositive();
    }
    
    @Test
    public void compareTo_WhenComparingWithNull_ShouldThrowNullPointerException() {
        BookId id1 = new BookId(2);
        BookId id2 = null;
        
        ThrowingCallable actual = () -> id1.compareTo(id2);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
}
