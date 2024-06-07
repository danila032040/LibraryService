package domain.user.specifications;

import java.util.Objects;

import base.specification.Specification;
import domain.user.User;
import domain.user.UserId;

public class UserByIdSpecification implements Specification<User> {
    
    private final UserId userId;
    
    public UserByIdSpecification(UserId userId) {
        Objects.requireNonNull(userId);
        this.userId = userId;
    }
    
    @Override
    public boolean isSatisfiedBy(User user) {
        Objects.requireNonNull(user);
        return user.getId().equals(userId);
    }
}
