package domain.user;

import base.repository.Repository;
import base.specification.Specification;

public interface UserRepository extends Repository<User, UserId> {
    public UserId generateNewUserId();
    
    public boolean exists(Specification<User> specification);
}
