package domain.user;

import base.repository.Repository;
import base.specification.Specification;

public interface UserRepository extends Repository<User, UserId> {
    public boolean exists(Specification<User> specification);
    
    public UserId generateNewUserId();
}
