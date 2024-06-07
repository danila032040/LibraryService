package domain.author.specifications;

import java.util.Objects;

import base.specification.Specification;
import domain.author.Author;
import domain.author.AuthorId;

public class AuthorByIdSpecification implements Specification<Author> {
    private final AuthorId authorId;
    
    public AuthorByIdSpecification(AuthorId authorId) {
        Objects.requireNonNull(authorId);
        this.authorId = authorId;
    }
    
    @Override
    public boolean isSatisfiedBy(Author author) {
        Objects.requireNonNull(author);
        return author.getId().equals(authorId);
    }
}
