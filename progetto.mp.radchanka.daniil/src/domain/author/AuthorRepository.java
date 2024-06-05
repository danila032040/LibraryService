package domain.author;

import base.repository.Repository;
import base.specification.Specification;

public interface AuthorRepository extends Repository<Author, AuthorId> {
    public AuthorId generateNewAuthorId();
    
    public boolean exists(Specification<Author> specification);
}
