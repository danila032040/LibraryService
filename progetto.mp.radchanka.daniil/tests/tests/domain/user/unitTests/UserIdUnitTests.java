package tests.domain.user.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import domain.user.UserId;

public class UserIdUnitTests {
    @Test
    public void compareTo_WhenComparingWithGreaterId_ShouldReturnNegative() {
        UserId id1 = new UserId(1);
        UserId id2 = new UserId(2);
        
        int result = id1.compareTo(id2);
        
        assertThat(result).isNegative();
    }
    
    @Test
    public void compareTo_WhenComparingWithLesserId_ShouldReturnPositive() {
        UserId id1 = new UserId(2);
        UserId id2 = new UserId(1);
        
        int result = id1.compareTo(id2);
        
        assertThat(result).isPositive();
    }
    
    @Test
    public void compareTo_WhenComparingWithNull_ShouldThrowNullPointerException() {
        UserId id1 = new UserId(2);
        UserId id2 = null;
        
        ThrowingCallable actual = () -> id1.compareTo(id2);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void compareTo_WhenIdsAreEqual_ShouldReturnZero() {
        UserId id1 = new UserId(1);
        UserId id2 = new UserId(1);
        
        int result = id1.compareTo(id2);
        
        assertThat(result).isZero();
    }
    
    @Test
    public void createClone_ShouldReturnEqualButNotTheSameInstance() {
        UserId id = new UserId(0);
        
        UserId actual = id.createClone();
        
        assertThat(actual).isNotSameAs(id).isEqualTo(id);
    }
    
    @Test
    public void equals_WhenComparedWithTheDifferentClassObject_ShouldBeFalse() {
        UserId id = new UserId(1);
        Integer otherInstance = 5;
        
        @SuppressWarnings("unlikely-arg-type")
        boolean actual = id.equals(otherInstance);
        
        assertThat(actual).isFalse();
    }
    
    @Test
    public void equals_WhenComparingWithTheSameInstance_ShouldBeTrue() {
        UserId id = new UserId(1);
        
        boolean actual = id.equals(id);
        
        assertThat(actual).isTrue();
    }
    
    @Test
    public void equals_WhenIdsAreEqual_ShouldBeTrue() {
        UserId id1 = new UserId(5);
        UserId id2 = new UserId(5);
        
        boolean actual = id1.equals(id2);
        
        assertThat(actual).isTrue();
    }
    
    @Test
    public void equals_WhenIdsAreNotEqual_ShouldBeFalse() {
        UserId id1 = new UserId(5);
        UserId id2 = new UserId(2);
        
        boolean actual = id1.equals(id2);
        
        assertThat(actual).isFalse();
    }
    
    @Test
    public void hashCode_WhenIdsAreEqual_HashCodesShouldBeEqualForBothInstances() {
        UserId id1 = new UserId(5);
        UserId id2 = new UserId(5);
        
        int actual1 = id1.hashCode();
        int actual2 = id2.hashCode();
        
        assertThat(actual1).isEqualTo(actual2);
    }
    
    @Test
    public void hashCode_WhenIdsAreNotEqual_HashCodesShouldBeNotEqualForBothInstances() {
        UserId id1 = new UserId(5);
        UserId id2 = new UserId(2);
        
        int actual1 = id1.hashCode();
        int actual2 = id2.hashCode();
        
        assertThat(actual1).isNotEqualTo(actual2);
    }
}
