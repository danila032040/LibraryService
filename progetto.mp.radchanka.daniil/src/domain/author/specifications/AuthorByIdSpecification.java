package domain.author.specifications;

import base.specification.Specification;
import domain.author.Author;
import domain.author.AuthorId;

public class AuthorByIdSpecification implements Specification<Author> {
    private final AuthorId authorId;
    
    public AuthorByIdSpecification(AuthorId authorId) {
        this.authorId = authorId;
    }
    
    @Override
    public boolean isSatisfiedBy(Author author) {
        return author.getId().equals(authorId);
    }
}
