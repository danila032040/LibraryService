package tests.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;
import java.util.Optional;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.assertj.core.util.Lists;
import org.junit.Test;

import base.specification.Specification;
import domain.common.Address;
import domain.user.User;
import domain.user.UserId;
import infrastructure.user.repositories.UserRepositoryImpl;

public class UserRepositoryImplUnitTests {
    @Test
    public void createInstance_WhenEntityCloneFactoryFactoryIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new UserRepositoryImpl(new ArrayList<>(), ArrayList::new, null);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void createInstance_WhenResultCollectionFactoryIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new UserRepositoryImpl(new ArrayList<>(), null, User::createClone);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void createInstance_WhenStorageIsNull_ShouldThrowNullPointerException() {
        ThrowingCallable actual = () -> new UserRepositoryImpl(null, ArrayList::new, User::createClone);
        
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(actual);
    }
    
    @Test
    public void exists_WhenStorageIsEmpty_ShouldReturnFalse() {
        UserRepositoryImpl userRepository = new UserRepositoryImpl(
                new ArrayList<>(),
                ArrayList::new,
                User::createClone);
        
        Specification<User> specification = u -> u.getId().equals(new UserId(1));
        
        boolean result = userRepository.exists(specification);
        
        assertThat(result).isFalse();
    }
    
    @Test
    public void exists_WhenStorageIsNotEmptyAndNoUserSatisfiesSpecification_ShouldReturnFalse() {
        User user = User.createNewUser(new UserId(1), "", "", new Address(), Optional.empty());
        UserRepositoryImpl userRepository = new UserRepositoryImpl(
                Lists.newArrayList(user),
                ArrayList::new,
                User::createClone);
        
        Specification<User> specification = u -> u.getId().equals(new UserId(2));
        
        boolean result = userRepository.exists(specification);
        
        assertThat(result).isFalse();
    }
    
    @Test
    public void exists_WhenStorageIsNotEmptyAndUserSatisfiesSpecification_ShouldReturnTrue() {
        User user = User.createNewUser(new UserId(1), "", "", new Address(), Optional.empty());
        UserRepositoryImpl userRepository = new UserRepositoryImpl(
                Lists.newArrayList(user),
                ArrayList::new,
                User::createClone);
        
        Specification<User> specification = u -> u.getId().equals(new UserId(1));
        
        boolean result = userRepository.exists(specification);
        
        assertThat(result).isTrue();
    }
    
    @Test
    public void generateNewUserId_WhenStorageIsEmpty_ShouldReturnUserIdZero() {
        UserRepositoryImpl userRepository = new UserRepositoryImpl(
                new ArrayList<>(),
                ArrayList::new,
                User::createClone);
        
        UserId newUserId = userRepository.generateNewUserId();
        
        assertThat(newUserId.getId()).isEqualTo(0);
    }
    
    @Test
    public void generateNewUserId_WhenStorageIsNotEmpty_ShouldReturnNextUserId() {
        UserRepositoryImpl userRepository = new UserRepositoryImpl(
                Lists
                        .newArrayList(
                                User.createNewUser(new UserId(1), "", "", new Address(), Optional.empty()),
                                User.createNewUser(new UserId(2), "", "", new Address(), Optional.empty()),
                                User.createNewUser(new UserId(5), "", "", new Address(), Optional.empty())),
                ArrayList::new,
                User::createClone);
        
        UserId newUserId = userRepository.generateNewUserId();
        
        assertThat(newUserId.getId()).isEqualTo(6);
    }
}
