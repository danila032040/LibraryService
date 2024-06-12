package domain.author;

import base.repository.Repository;
import base.specification.Specification;

public interface AuthorRepository extends Repository<Author, AuthorId> {
    public boolean exists(Specification<Author> specification);
    
    public AuthorId generateNewAuthorId();
}
