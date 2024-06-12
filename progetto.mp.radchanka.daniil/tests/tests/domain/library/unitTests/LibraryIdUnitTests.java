package tests.domain.library.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import domain.library.LibraryId;

public class LibraryIdUnitTests {
    @Test
    public void compareTo_WhenComparingWithGreaterId_ShouldReturnNegative() {
        LibraryId id1 = new LibraryId(1);
        LibraryId id2 = new LibraryId(2);
        
        int result = id1.compareTo(id2);
        
        assertThat(result).isNegative();
    }
    
    @Test
    public void compareTo_WhenComparingWithLesserId_ShouldReturnPositive() {
        LibraryId id1 = new LibraryId(2);
        LibraryId id2 = new LibraryId(1);
        
        int result = id1.compareTo(id2);
        
        assertThat(result).isPositive();
    }
    
    @Test
    public void compareTo_WhenComparingWithNull_ShouldThrowNullPointerException() {
        LibraryId id1 = new LibraryId(2);
        LibraryId id2 = null;
        
        ThrowingCallable actual = () -> id1.compareTo(id2);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void compareTo_WhenIdsAreEqual_ShouldReturnZero() {
        LibraryId id1 = new LibraryId(1);
        LibraryId id2 = new LibraryId(1);
        
        int result = id1.compareTo(id2);
        
        assertThat(result).isZero();
    }
    
    @Test
    public void createClone_ShouldReturnEqualButNotTheSameInstance() {
        LibraryId id = new LibraryId(0);
        
        LibraryId actual = id.createClone();
        
        assertThat(actual).isNotSameAs(id).isEqualTo(id);
    }
    
    @Test
    public void equals_WhenComparedWithTheDifferentClassObject_ShouldBeFalse() {
        LibraryId id = new LibraryId(1);
        Integer otherInstance = 5;
        
        @SuppressWarnings("unlikely-arg-type")
        boolean actual = id.equals(otherInstance);
        
        assertThat(actual).isFalse();
    }
    
    @Test
    public void equals_WhenComparingWithTheSameInstance_ShouldBeTrue() {
        LibraryId id = new LibraryId(1);
        
        boolean actual = id.equals(id);
        
        assertThat(actual).isTrue();
    }
    
    @Test
    public void equals_WhenIdsAreEqual_ShouldBeTrue() {
        LibraryId id1 = new LibraryId(5);
        LibraryId id2 = new LibraryId(5);
        
        boolean actual = id1.equals(id2);
        
        assertThat(actual).isTrue();
    }
    
    @Test
    public void equals_WhenIdsAreNotEqual_ShouldBeFalse() {
        LibraryId id1 = new LibraryId(5);
        LibraryId id2 = new LibraryId(2);
        
        boolean actual = id1.equals(id2);
        
        assertThat(actual).isFalse();
    }
    
    @Test
    public void hashCode_WhenIdsAreEqual_HashCodesShouldBeEqualForBothInstances() {
        LibraryId id1 = new LibraryId(5);
        LibraryId id2 = new LibraryId(5);
        
        int actual1 = id1.hashCode();
        int actual2 = id2.hashCode();
        
        assertThat(actual1).isEqualTo(actual2);
    }
    
    @Test
    public void hashCode_WhenIdsAreNotEqual_HashCodesShouldBeNotEqualForBothInstances() {
        LibraryId id1 = new LibraryId(5);
        LibraryId id2 = new LibraryId(2);
        
        int actual1 = id1.hashCode();
        int actual2 = id2.hashCode();
        
        assertThat(actual1).isNotEqualTo(actual2);
    }
}
