package domain.user.specifications;

import base.specification.Specification;
import domain.user.User;
import domain.user.UserId;

public class UserByIdSpecification implements Specification<User> {

	private UserId userId;

	public UserByIdSpecification(UserId userId) {
		this.userId = userId;
	}

	@Override
	public boolean isSatisfiedBy(User user) {
		return user.getId().equals(userId);
	}
}
