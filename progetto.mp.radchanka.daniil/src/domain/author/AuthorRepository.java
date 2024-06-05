package domain.author;

import base.repository.Repository;

public interface AuthorRepository extends Repository<Author, AuthorId> {
    public AuthorId generateNewAuthorId();
}
