package tests.domain.user.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Optional;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

import domain.common.Address;
import domain.user.User;
import domain.user.UserId;
import domain.user.specifications.UserByIdSpecification;

public class UserByIdSpecificationUnitTests {
    @Test
    public void constructor_WhenUserIdIsNull_ShouldThrowException() {
        ThrowingCallable actual = () -> new UserByIdSpecification(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void isSatisfiedBy_WhenUserIsNull_ShouldThrowException() {
        UserId userId = new UserId(1);
        UserByIdSpecification specification = new UserByIdSpecification(userId);
        
        ThrowingCallable actual = () -> specification.isSatisfiedBy(null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void isSatisfiedBy_WhenUserIdMatches_ShouldReturnTrue() {
        UserId userId = new UserId(1);
        UserByIdSpecification specification = new UserByIdSpecification(userId);
        User user = User.createNewUser(userId, "", "", new Address(), Optional.empty());
        
        boolean result = specification.isSatisfiedBy(user);
        
        assertThat(result).isTrue();
    }
    
    @Test
    public void isSatisfiedBy_WhenUserIdDoesNotMatch_ShouldReturnFalse() {
        UserId userId1 = new UserId(1);
        UserId userId2 = new UserId(2);
        UserByIdSpecification specification = new UserByIdSpecification(userId1);
        User user = User.createNewUser(userId2, "", "", new Address(), Optional.empty());
        
        boolean result = specification.isSatisfiedBy(user);
        
        assertThat(result).isFalse();
    }
}
